package com.gorbatenkov.FriendLove.controllers;

import com.gorbatenkov.FriendLove.models.Message;
import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.security.UserDetails;
import com.gorbatenkov.FriendLove.services.MessagesService;
import com.gorbatenkov.FriendLove.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Данный контроллер управляет всеми действиями,
 * связанными с сообщениями,
 * включая чтение диалогов, отправку
 * сообщений и отображение их на соответствующих страницах.
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MODER','ROLE_ADMIN')")
// Базовый URL-путь для всех методов контроллера.
@RequestMapping("/messages")
public class MessageController {

    private final UserService userService;
    private final MessagesService messagesService;

    /**
     * Внедрение зависимостей.
     *
     * @param userService     сервис для работы с пользователями
     * @param messagesService сервис для работы с приложениями
     */
    public MessageController(UserService userService, MessagesService messagesService) {
        this.userService = userService;
        this.messagesService = messagesService;
    }

    /**
     * Метод для получения текущего пользователя, авторизованного в системе.
     *
     * @return пользователь, авторизованный в системе
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findByPhoneNumber(userDetails.user().getPhoneNumber()).get();
    }

    /**
     * Обрабатывает GET-запросы на /messages.
     * Формирует модель с данными о диалогах
     * пользователя и его собеседниках для отображения
     * на странице сообщений.
     *
     * @param model модель данных
     * @return страница messages
     */
    @GetMapping("")
    public String toMessages(Model model) {
        User authorizedUser = getCurrentUser();
        List<Message> receiverList = messagesService.findBySender(authorizedUser);
        List<Message> senderList = messagesService.findByReceiver(authorizedUser);
        Set<Integer> companion = new HashSet<>();

        for (Message message : receiverList) {
            companion.add(message.getReceiver().getId());
        }
        for (Message message : senderList) {
            companion.add(message.getSender().getId());
        }
        List<User> companions = new ArrayList<>();
        List<Message> companionsLastMessage = new ArrayList<>();
        for (Integer id : companion) {
            User compan = userService.findById(id).get();
            companions.add(compan);
            companionsLastMessage.add(messagesService.findBySenderAndReceiverLastMessage(authorizedUser, compan));
        }

        model.addAttribute("authorizedUser", authorizedUser);
        model.addAttribute("companions", companions);
        model.addAttribute("companionsLastMessage", companionsLastMessage);

        return "/user/messages";
    }

    /**
     * Обрабатывает GET-запросы для отображения страницы диалога с конкретным пользователем.
     * Формирует модель с данными о сообщениях в диалоге для отображения на странице диалога.
     *
     * @param model модель данных
     * @param id    идентификатор конкретного пользователя
     * @return страница dialog-window
     */
    @GetMapping("/{id}")
    public String messageDialog(Model model, @PathVariable(name = "id") int id) {
        User authorizedUser = getCurrentUser();
        User companion = userService.findById(id).get();
        List<Message> companionMessages = new ArrayList<>();
        companionMessages.addAll(messagesService.findBySenderAndReceiver(authorizedUser, companion));
        companionMessages.addAll(messagesService.findBySenderAndReceiver(companion, authorizedUser));
        companionMessages.sort(Comparator.comparing(Message::getCreatedAt));

        model.addAttribute("authorizedUser", authorizedUser);
        model.addAttribute("companion", companion);
        model.addAttribute("companionMessages", companionMessages);

        return "/user/dialog-window";
    }

    /**
     * Обрабатывает POST-запросы для отправки сообщений между пользователями.
     *
     * @param sender_id   идентификатор отправителя
     * @param receiver_id идентификатор получателя
     * @param message     текстовое сообщение
     * @return перенаправление на страницу messages
     */
    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam(value = "sender_id") int sender_id,
                              @RequestParam(value = "receiver_id") int receiver_id,
                              @RequestParam(value = "message") String message) {
        User sender = userService.findById(sender_id).get();
        User receiver = userService.findById(receiver_id).get();
        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setReceiver(receiver);
        newMessage.setSender(sender);
        newMessage.setCreatedAt(LocalDateTime.now());
        messagesService.save(newMessage);

        return "redirect:/messages";
    }

}
