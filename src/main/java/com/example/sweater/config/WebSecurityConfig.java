package com.example.sweater.config;

import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //при старте приложения конфигурирует веб секьюрити
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //чтобы работала аннотация @PreAuthorize("hasAuthority('ADMIN')") в UserController
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//авторизация, ** - url продолжается до беск., * - url содержит еще 1 сегмент
                    .antMatchers("/", "/registration", "/static/**", "/activate/*").permitAll()//на главной стр разрешаем полный доступ(без аторизации)
                    .anyRequest().authenticated() //для всех остальных запросов требуем авторизацию
                .and()
                    .formLogin() //включаем форм логин
                    .loginPage("/login") //логин на этом мэппинге
                    .permitAll() //разрешаем этим пользоваться всем
                .and()
                    .rememberMe()
                .and()
                    .logout() //выход
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}