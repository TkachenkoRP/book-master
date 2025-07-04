package com.my.bookmaster.security;

import com.my.bookmaster.model.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class AppUserPrincipal implements UserDetails {

    private final UserAuth userAuth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAuth.getRoles().stream()
                .map(roleType -> new SimpleGrantedAuthority(roleType.name())).toList();
    }

    @Override
    public String getPassword() {
        return userAuth.getPassword();
    }

    @Override
    public String getUsername() {
        return userAuth.getName();
    }

    public String getEmail() {
        return userAuth.getEmail();
    }

    public Long getId() {
        return userAuth.getId();
    }
}
