/**
 * Конфигурация Spring Security для моего проекта.
 * */


package com.example.firstproject.config;

import com.example.firstproject.model.Permission;
import com.example.firstproject.model.Role;
import com.example.firstproject.security_service.UserDeteilsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SecurityConfig - класс унаследованный от WebSecurityConfigurerAdapter являющимся стандартными настройка для
 * Spring Security.
 * */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final UserDetailsService userDeteilsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDeteilsServiceImpl") UserDetailsService userDeteilsService) {
        this.userDeteilsService = userDeteilsService;
    }

    /**
     *  Настройки HttpSecurity т.к. работа осуществляется c HTTP Client.
     *  Настравиаем следующим образом:
     *  Следующие страницы с Get запросом не будут блокироваться Spring Security:
     *      "/auth/registration", "/people/users", "/","/restorePass"
     *  Реализация пользователей с разными "уровнями разрешения" для Spring Security:
     *      Не зарегестрированный пользователь может получать только Get запросы,
     *      Зарегестрированный может Get и Post,
     *      Админ может удалять;
     *  Страница для логирования находится по адресу "/auth/login" послу еспешного входа нас перебрасывает на
     *      главную страницу
     *  Выход (logout) перебрасывает нас на страницу "/auth/login".
     * */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/registration", "/people/users", "/","/restorePass").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").hasAuthority(Permission.PERMISSION_READ.getPermission())
                .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(Permission.PERMISSION_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(Permission.PERMISSION_WRITE.getPermission())
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/login").defaultSuccessUrl("/").permitAll()
            .and()
                .logout().logoutSuccessUrl("/auth/login");
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
//                .clearAuthentication(true).logoutSuccessUrl("/auth/login");

    }

    /**
     * Заполнение Authentification для того, то бы Spring Security знал о том какие пользователи могут находиться
     *      в системе;
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    /**
     * Метод для шифрования пароля. Но в данной реализации проекта я не шифрую пароль.
     * */
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12);
    }

    /**
     * создание и заполнение своего DaoAuthenticationProvider.
     * */
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setUserDetailsService(userDeteilsService);
        return daoAuthenticationProvider;
    }

}
