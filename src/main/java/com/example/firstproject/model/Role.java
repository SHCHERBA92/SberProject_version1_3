/**
 * перечисление ролей пользователя.
 * У каждой роли есть свой Premission.
 *
 * SimpleGrantedAuthority - сущность о которой знает Spring Security.
 *      Эта сущность позволяет определить Spring Security связь доступов.
*  Мы конвертируем наши роли в SimpleGrantedAuthority
 * */
package com.example.firstproject.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.PERMISSION_READ)),
    ADMIN(Set.of(Permission.PERMISSION_READ, Permission.PERMISSION_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities()
    {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
