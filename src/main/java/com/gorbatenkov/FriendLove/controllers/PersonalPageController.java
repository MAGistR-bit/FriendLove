package com.gorbatenkov.FriendLove.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gorbatenkov.FriendLove.models.Post;
import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.models.UserPhoto;
import com.gorbatenkov.FriendLove.security.UserDetails;
import com.gorbatenkov.FriendLove.services.RequestFriendshipService;
import com.gorbatenkov.FriendLove.services.UserPhotoService;
import com.gorbatenkov.FriendLove.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

/**
 * Данный контроллер отвечает за отображение
 * информации о пользователе, его постах и фотографиях
 * на его личной странице, а также управление друзьями
 * и запросами на добавление в друзья
 */
@Controller
// Контроллер доступен только авторизованным пользователям со следующими ролями
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MODER','ROLE_ADMIN')")
@RequestMapping("/user")
public class PersonalPageController {
    private final UserService userService;
    private final UserPhotoService userPhotoService;

    private final RequestFriendshipService requestFriendshipService;

    /**
     * Внедрение зависимостей
     *
     * @param userService              сервис для работы с пользователями
     * @param userPhotoService         сервис для работы с фотографиями пользователей
     * @param requestFriendshipService сервис для работы с запросами на добавление в друзья
     */
    public PersonalPageController(UserService userService, UserPhotoService userPhotoService, RequestFriendshipService requestFriendshipService) {
        this.userService = userService;
        this.userPhotoService = userPhotoService;
        this.requestFriendshipService = requestFriendshipService;
    }

    /**
     * Получает текущего пользователя, авторизованного в системе.
     *
     * @return пользователь, авторизованный в системе.
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findByPhoneNumber(userDetails.getUser().getPhoneNumber()).get();
    }

    /**
     * Отвечает за отображение личной страницы пользователя.
     * Запрашивает информацию о пользователе, его друзьях,
     * группах, фотографиях и постах. Затем сортирует
     * посты и фотографии по дате создания.
     *
     * @param model модель данных
     * @param id    идентификатор
     * @param post  пост/публикация
     * @return страница profile
     * @throws JsonProcessingException исключение, которое может возникнуть
     */
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public String toProfile(Model model, @PathVariable(name = "id") int id,
                            @ModelAttribute(name = "post") Post post) throws JsonProcessingException {
        User currentUser = userService.findById(id).get();

        currentUser.getSubscriptionGroup().size();
        currentUser.getFriends().size();
        currentUser.getSubscribers().size();
        currentUser.getPosts().size();
        currentUser.getUserPhotos().size();

        for (Post p : currentUser.getPosts()) {
            System.out.println(p.getJsonn());
        }

        List<Post> postList = currentUser.getPosts();
        List<UserPhoto> userPhotos = currentUser.getUserPhotos();

        for (UserPhoto user_Photo : userPhotos) {
            user_Photo.getUserPhotoComments().size();
        }

        postList.sort(Comparator.comparing(Post::getCreatedAt));
        userPhotos.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
        userPhotos.remove(userPhotos.size() - 1);

        User authorizedUser = getCurrentUser();
        authorizedUser.getFriends().size();

        /* Переменная, которая определяет вариант отображения
        кнопки для добавления в друзья на личной странице
        в зависимости от статуса дружбы или запроса*/
        int displayButtonVariant;

        if (authorizedUser.getFriends().contains(currentUser)) {
            displayButtonVariant = 1;
        } else if (requestFriendshipService.findBySenderAndReceiver(authorizedUser, currentUser).isPresent()) {
            displayButtonVariant = 2;
        } else {
            displayButtonVariant = 3;
        }

        /* Добавляем все необходимые данные в модель (Model),
        которые будут использоваться для отображения
        на личной странице пользователя. */
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("authorizedUser", authorizedUser);
        model.addAttribute("displayButtonVariant", displayButtonVariant);
        model.addAttribute("groups", currentUser.getSubscriptionGroup());
        model.addAttribute("friends", currentUser.getFriends());
        model.addAttribute("subscribers", currentUser.getSubscribers());
        model.addAttribute("posts", postList);
        model.addAttribute("photos", currentUser.getUserPhotos());
        model.addAttribute("formt", "%02d");
        model.addAttribute("photos", userPhotos);

        UserPhoto avatar = userPhotoService.findByPath(currentUser.getAvatar()).get();
        model.addAttribute("avatar", avatar);

        for (UserPhoto userPhoto : currentUser.getUserPhotos()) {
            System.out.println(userPhoto.getPhotoPath());
        }
        return "/user/profile";
    }
}
