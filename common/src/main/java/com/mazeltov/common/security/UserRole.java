package com.mazeltov.common.security;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;

import java.util.*;
import java.util.stream.*;

import static com.mazeltov.common.security.UserPermission.*;

public enum UserRole {
    GUEST(
            Stream.of(PRODUCT_READ, REVIEW_READ)
                    .collect(Collectors.toSet())
    ), USER(
            Stream.of(PRODUCT_READ, REVIEW_READ, REVIEW_WRITE, REVIEW_EDIT)
                    .collect(Collectors.toSet())

    ), ADMIN(Stream.of(UserPermission.values())
            .collect(Collectors.toSet()));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> grantedAuthorities() {
        Set<GrantedAuthority> authorities = getPermissions()
                .stream()
                .map(e -> new SimpleGrantedAuthority(e.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
