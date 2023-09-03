package com.gorbatenkov.FriendLove.controllers;

import com.gorbatenkov.FriendLove.models.Friend;
import com.gorbatenkov.FriendLove.models.RequestFriendship;
import com.gorbatenkov.FriendLove.models.Subscriber;
import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.security.UserDetails;
import com.gorbatenkov.FriendLove.services.FriendsService;
import com.gorbatenkov.FriendLove.services.RequestFriendshipService;
import com.gorbatenkov.FriendLove.services.SubscribersService;
import com.gorbatenkov.FriendLove.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер Spring Boot для управления друзьями
 * и запросами на дружбу пользователей.
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MODER','ROLE_ADMIN')")
// Определяет базовый URL-путь для всех методов контроллера.
@RequestMapping("/friends")
public class FriendController {

    private final UserService userService;
    private final RequestFriendshipService requestFriendshipService;
    private final FriendsService friendsService;

    private final SubscribersService subscribersService;

    /**
     * Внедрение зависимостей, таких как сервисы для работы с пользователями,
     * запросами на дружбу, друзьями и подписчиками.
     *
     * @param userService              сервис, предназначенный для работы с пользователями
     * @param requestFriendshipService сервис, отвечающий на заявки в друзья
     * @param friendsService           сервис, отвечающий за друзей
     * @param subscribersService       сервис, отвечающий за подписчиков
     */
    public FriendController(UserService userService,
                            RequestFriendshipService requestFriendshipService,
                            FriendsService friendsService,
                            SubscribersService subscribersService) {
        this.userService = userService;
        this.requestFriendshipService = requestFriendshipService;
        this.friendsService = friendsService;
        this.subscribersService = subscribersService;
    }

    /**
     * Метод, который используется для получения
     * текущего пользователя, авторизованного в системе.
     *
     * @return пользователь, авторизованный в системе
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findByPhoneNumber(userDetails.getUser().getPhoneNumber()).get();
    }

    /**
     * Обрабатывает GET-запросы на /friends.
     * Этот метод отображает страницу друзей пользователя.
     * Он также поддерживает поиск друзей по имени и фамилии.
     *
     * @param model      модель данных
     * @param searchLine поисковый запрос
     * @return веб-страница, расположенная по адресу /user/friends
     */
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toFriendsPage(Model model,
                                @RequestParam(value = "q", defaultValue = "")
                                String searchLine) {
        User currentUser = getCurrentUser();
        List<User> friends = new ArrayList<>();
        List<User> searchResult = new ArrayList<>();

        if (searchLine.isEmpty()) {
            friends = currentUser.getFriends();
        } else {
            String[] splited = searchLine.split(" ");
            if (splited.length == 1) {
                searchResult = userService.findBySearchLine(splited[0], "");
            } else if (splited.length == 2) {
                searchResult = userService.findBySearchLine(splited[0], splited[1]);
            }
            searchResult.remove(currentUser);

        }

        ArrayList<Integer> friendsId = new ArrayList<>();
        for (User user : currentUser.getFriends()) {
            friendsId.add(user.getId());
        }

        List<User> receiverList = requestFriendshipService.findBySender(currentUser);

        ArrayList<Integer> receiversId = new ArrayList<>();
        for (User user : receiverList) {
            receiversId.add(user.getId());
        }

        model.addAttribute("friends", friends);
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("receiversId", receiversId);
        model.addAttribute("friendsId", friendsId);
        model.addAttribute("authorizedUser", currentUser);

        return "/user/friends";
    }


    /**
     * Метод, который обрабатывает POST-запросы на /friends/request-friendship.
     * Этот метод отправляет запрос на дружбу между двумя пользователями.
     *
     * @param sender_id   идентификатор отправителя
     * @param receiver_id идентификатор получателя
     * @return перенаправление на страницу friends
     */
    @PostMapping("/request-friendship")
    public String sendRequest(@RequestParam(value = "sender_id") int sender_id,
                              @RequestParam(value = "receiver_id") int receiver_id) {
        RequestFriendship requestFriendship = new RequestFriendship();
        requestFriendship.setSender(userService.findById(sender_id).get());
        requestFriendship.setReceiver(userService.findById(receiver_id).get());
        requestFriendshipService.save(requestFriendship);

        return "redirect:/friends";
    }

    /**
     * Метод, который обрабатывает GET-запросы на /friends/requests.
     * Этот метод отображает страницу с запросами на дружбу,
     * полученными пользователем.
     *
     * @param model модель данных
     * @return страница friendship-request
     */
    @GetMapping("/requests")
    public String friendshipRequests(Model model) {
        User currentUser = getCurrentUser();

        List<User> senders;

        senders = requestFriendshipService.findByReceiver(currentUser);


        model.addAttribute("authorizedUser", currentUser);
        model.addAttribute("senders", senders);

        return "/user/friendship-request";
    }

    /**
     * Метод, который обрабатывает POST-запросы на /friends/accept-friendship.
     * Этот метод принимает запрос на дружбу и создает записи
     * о дружбе и подписке между двумя пользователями.
     *
     * @param user_id   идентификатор пользователя
     * @param sender_id идентификатор отправителя заявки на дружбу
     * @return перенаправление на /friends/requests
     */
    @PostMapping("/accept-friendship")
    public String acceptFriendship(@RequestParam(value = "user_id") int user_id,
                                   @RequestParam(value = "sender_id") int sender_id) {
        User receiver = userService.findById(user_id).get();
        User sender = userService.findById(sender_id).get();

        requestFriendshipService.deleteBySenderAndReceiver(sender, receiver);

        Friend friendMutual1 = new Friend();
        Friend friendMutual2 = new Friend();
        friendMutual1.setUser(receiver);
        friendMutual1.setFriend(sender);
        friendMutual2.setFriend(receiver);
        friendMutual2.setUser(sender);

        friendsService.save(friendMutual1);
        friendsService.save(friendMutual2);

        Subscriber subscriberMutual1 = new Subscriber();
        Subscriber subscriberMutual2 = new Subscriber();

        subscriberMutual1.setUser(receiver);
        subscriberMutual1.setSubscriber(sender);
        subscriberMutual2.setSubscriber(receiver);
        subscriberMutual2.setUser(sender);

        subscribersService.save(subscriberMutual1);
        subscribersService.save(subscriberMutual2);

        return "redirect:/friends/requests";
    }

    /**
     * Метод, который обрабатывает POST-запросы
     * на /friends/decline-friendship.
     * Этот метод отклоняет запрос на дружбу.
     *
     * @param user_id   идентификатор пользователя
     * @param sender_id идентификатор пользователя, который подал заявку на
     *                  дружбу
     * @return перенаправление на /friends/requests
     */
    @PostMapping("/decline-friendship")
    public String declineFriendship(@RequestParam(value = "user_id") int user_id,
                                    @RequestParam(value = "sender_id") int sender_id) {
        User receiver = userService.findById(user_id).get();
        User sender = userService.findById(sender_id).get();
        requestFriendshipService.deleteBySenderAndReceiver(sender, receiver);
        return "redirect:/friends/requests";
    }
}
