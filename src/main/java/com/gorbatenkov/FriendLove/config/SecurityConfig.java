package com.gorbatenkov.FriendLove.config;

import com.gorbatenkov.FriendLove.services.UserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Конфигурация безопасности для веб-приложения.
 * Определяет правила доступа к
 * разным URL и указывает способы аутентификации пользователей,
 * включая хэширование паролей.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    /**
     * Внедрение зависимости
     * @param userDetailsService аутентификация пользователей
     */
    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Метод настройки правил доступа к ресурсам приложения
     * @param http HTTP-запрос
     * @throws Exception исключение, которое может возникнуть
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Отключается CSRF защита
        http.csrf().disable()
                // Правила доступа к /admin/**
                .authorizeRequests()
                .antMatchers("/admin/**")
                .hasAnyRole("MODER", "ADMIN")
                .and()
                .authorizeRequests()
                // Доступны всем пользователям
                .antMatchers("/auth/login", "/auth/registration",
                        "/",
                        "/products/**", "/error").permitAll()
                .anyRequest().hasAnyRole("USER", "MODER", "ADMIN")
                .and()
                // Настраивается форма входа и выхода
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error").and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login");
    }

    /**
     * Метод настройки аутентификации.
     * Здесь устанавливается `userDetailsService` и шифруется пароль.
     *
     * @param auth объект класса AuthenticationManagerBuilder
     * @throws Exception исключение, которое может возникнуть
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    /**
     * Метод создания BCryptPasswordEncoder и его настройки
     * как компонента Spring Bean.
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Метод для создания экземпляров ModelMapper.
     * Используется в приложении для маппинга (преобразования) объектов
     * @return ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Метод для создания экземпляров ObjectMapper.
     * Используется в приложении для работы с JSON.
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
