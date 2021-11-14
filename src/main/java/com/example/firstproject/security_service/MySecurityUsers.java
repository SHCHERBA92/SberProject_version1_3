/**
 * класс унаследованный от UserDetails для того, что бы переделать мою сущность Users в User(Spring Security),
 * реализованно это чрезе метод "public static UserDetails userDetailsFromMyUsers(Users users)"
 * */

package com.example.firstproject.security_service;

import com.example.firstproject.model.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MySecurityUsers implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> grantedAuthorityList;
    private final boolean isActive;

    public MySecurityUsers(String username, String password, List<GrantedAuthority> grantedAuthorityList, boolean isActive) {
        this.username = username;
        this.password = password;
        this.grantedAuthorityList = grantedAuthorityList;
        this.isActive = isActive;
    }

//    public MySecurityUsers() {
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }



     public static UserDetails userDetailsFromMyUsers(Users users)
    {
        return new User(users.getEmail(), users.getPassword(),
                users.getStatus().name().equals("ACTIVE"),
                users.getStatus().name().equals("ACTIVE"),
                users.getStatus().name().equals("ACTIVE"),
                users.getStatus().name().equals("ACTIVE"),
                users.getRole().getSimpleGrantedAuthorities());
    }
}
