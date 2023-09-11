package com.gorbatenkov.FriendLove.security;

import com.gorbatenkov.FriendLove.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * Используется для предоставления информации о пользователе Spring Security
 * @param user пользователь
 */
public record UserDetails(User user) implements org.springframework.security.core.userdetails.UserDetails {

    /**
     * В реализации метода используется
     * единственная роль пользователя, полученная из объекта User.
     * @return коллекцию ролей пользователя в виде объекта GrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    /**
     * Получает пароль из объекта User
     * @return пароль пользователя
     */
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    /**
     * Получает имя пользователя, значение берется
     * из email пользователя.
     * @return имя пользователя
     */
    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    /* isAccountNonExpired(), isAccountNonLocked(),
    isCredentialsNonExpired(), isEnabled() - методы, которые указывают
    на состояние учетной записи пользователя.
    В моей реализации все методы возвращают `true`, что означает,
    что учетная запись всегда действительна и активна.*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
