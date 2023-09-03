package com.gorbatenkov.FriendLove.controllers;

import com.gorbatenkov.FriendLove.DTO.GroupPostDTO;
import com.gorbatenkov.FriendLove.DTO.PostDTO;
import com.gorbatenkov.FriendLove.DTO.UserPostDTO;
import com.gorbatenkov.FriendLove.models.Group;
import com.gorbatenkov.FriendLove.models.GroupPost;
import com.gorbatenkov.FriendLove.models.Post;
import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.security.UserDetails;
import com.gorbatenkov.FriendLove.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Контроллер Spring Boot, который управляет
 * страницей новостей пользователя
 */
@Controller
// Используется корневой URL
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    /**
     * Внедрение зависимостей, таких как сервис для
     * работы с пользователями (`userService`) и modelMapper.
     *
     * @param userService сервис для работы с пользователями
     * @param modelMapper объект класса ModelMapper
     */
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    /**
     * Метод для получения текущего пользователя, авторизованного в системе
     *
     * @return пользователь, авторизованный в системе
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findByPhoneNumber(userDetails.getUser().getPhoneNumber()).get();
    }

    /**
     * Метод для преобразования объектов Post в
     * соответствующие DTO (Data Transfer Object)
     * с использованием modelMapper.
     *
     * @param post объект класса Post
     * @return соответствующий DTO
     */
    private UserPostDTO UserPostToUserPostDTO(Post post) {
        return modelMapper.map(post, UserPostDTO.class);
    }

    /**
     * Метод для преобразования объектов GroupPost в
     * соответствующие DTO (Data Transfer Object) с использованием modelMapper.
     *
     * @param groupPost объект класса GroupPost
     * @return соответствующий DTO
     */
    private GroupPostDTO GroupPostToGroupPostDTO(GroupPost groupPost) {
        return modelMapper.map(groupPost, GroupPostDTO.class);
    }

    /**
     * В этом методе происходит сбор новостей пользователя,
     * включая его собственные посты, посты его друзей и
     * посты из групп, на которые он подписан.
     *
     * @param model модель данных
     * @return страница news
     * @throws JsonProcessingException исключение, которое может возникнуть
     */
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MODER','ROLE_ADMIN')")
    @GetMapping("/")
    @Transactional(readOnly = true)
    public String newsPage(Model model) throws JsonProcessingException {
        // Получение текущего пользователя
        User currentUser = getCurrentUser();
        // Список постов сообщества/группы
        List<GroupPost> groupPosts = new ArrayList<>();
        // Список постов пользователя
        List<Post> posts = new ArrayList<>();
        List<Group> groups = currentUser.getSubscriptionGroup();
        List<User> friends = currentUser.getFriends();

        System.out.println(groups.isEmpty());

        // Сбор публикаций из групп и друзей пользователя
        for (Group group : groups) {
            groupPosts.addAll(group.getGroupPosts());
        }
        for (User friend : friends) {
            posts.addAll(friend.getPosts());
        }

        posts.addAll(currentUser.getPosts());

        List<UserPostDTO> userPostsDTO = new ArrayList<>();
        List<GroupPostDTO> groupPostsDTO = new ArrayList<>();

        // Преобразование публикаций/постов в DTO.
        for (Post post : posts) {
            userPostsDTO.add(UserPostToUserPostDTO(post));
        }
        for (GroupPost post : groupPosts) {
            groupPostsDTO.add(GroupPostToGroupPostDTO(post));
        }

        for (GroupPostDTO post : groupPostsDTO) {
            // Создание множества для идентификации контактов группы
            Set<Integer> groupContactsId = new HashSet<>();
            // Установка идентификаторов контактов группы в DTO
            // для каждого поста сообщества
            for (User contact : post.getGroup().getContacts()) {
                groupContactsId.add(contact.getId());
            }
            post.setGroupContactsId(groupContactsId);
        }

        // Публикации объединяются в `postss` и сортируются по дате создания в убывающем порядке
        List<PostDTO> postss = new ArrayList<>();
        postss.addAll(userPostsDTO);
        postss.addAll(groupPostsDTO);

        postss.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));

        // Вывод публикаций в консоль для отладки.
        for (PostDTO post : postss) {
            System.out.println(post.getJsonn());
        }

        // Обновление model атрибутами, содержащими список постов,
        // текущего пользователя
        model.addAttribute("posts", postss);
        model.addAttribute("authorizedUser", getCurrentUser());

        return "/user/news";
    }
    
}
