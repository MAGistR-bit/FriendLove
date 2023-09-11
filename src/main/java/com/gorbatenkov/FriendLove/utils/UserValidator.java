package com.gorbatenkov.FriendLove.utils;

import com.gorbatenkov.FriendLove.models.User;
import com.gorbatenkov.FriendLove.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Данный класс позволяет произвести валидацию объекта
 * User перед его сохранением в базу данных или выполнением других операций.
 * UserValidator обеспечивает проверку на уникальность номера телефона,
 * что является важным для обеспечения уникальности пользователей в системе.
 */
// Аннотация, указывающая, что этот
// класс является компонентом Spring и должен быть
// управляемым контейнером Spring
@Component
public class UserValidator implements Validator {
    private final UserDetailsService userDetailsService;

    /**
     * Внедрение зависимости через конструктор
     *
     * @param userDetailsService сервис
     */
    @Autowired
    public UserValidator(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Проверяет, является ли класс User поддерживаемым
     * классом для валидации.
     *
     * @param clazz класс
     * @return результат проверки (true/false)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    /**
     * Метод пытается загрузить пользователя
     * по номеру телефона, используя userDetailsService.
     * Если пользователь с таким номером телефона существует,
     * то ошибка валидации добавляется в объект Errors, указывая,
     * что пользователь с таким номером телефона уже существует.
     * @param target объект, который должен быть валидирован
     * @param errors объект, который используется для записи ошибок валидации
     */
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userDetailsService.loadUserByUsername(user.getPhoneNumber());
        } catch (UsernameNotFoundException ignore) {
            return;
        }
        errors.rejectValue("phoneNumber", "",
                "Пользователь с таким номером телефона уже существует!");
    }
}
