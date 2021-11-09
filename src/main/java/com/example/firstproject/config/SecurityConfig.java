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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final UserDetailsService userDeteilsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDeteilsServiceImpl") UserDetailsService userDeteilsService) {
        this.userDeteilsService = userDeteilsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/registration", "/people/users", "/").permitAll()
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                    .username("admin")
//                    .password( NoOpPasswordEncoder.getInstance().encode("admin") )//passwordEncoder().encode("admin"))
//                    .authorities(Role.ADMIN.getSimpleGrantedAuthorities())
//                    .build(),
//
//                User.builder()
//                        .username("user")
//                        .password( NoOpPasswordEncoder.getInstance().encode("user"))  //passwordEncoder().encode("user"))
//                        .authorities(Role.USER.getSimpleGrantedAuthorities())
//                        .build()
//            );
//    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12);
    }

//    @Bean
//    PasswordEncoder noPasswordEncoder(){
//        return new NoOpPasswordEncoder();
//    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setUserDetailsService(userDeteilsService);
        return daoAuthenticationProvider;
    }

}
