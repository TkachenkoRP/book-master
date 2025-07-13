package com.my.bookmaster.service.impl;

import com.my.bookmaster.exception.EntityNotFoundException;
import com.my.bookmaster.model.RoleType;
import com.my.bookmaster.model.UserAuth;
import com.my.bookmaster.repository.UserRepository;
import com.my.bookmaster.security.AppUserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private Authentication authentication;
    private UserAuth user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        AppUserPrincipal userPrincipal = new AppUserPrincipal(new UserAuth(1L, "test@example.com", "password", "Test User", Set.of(RoleType.ROLE_USER)));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getPrincipal()).thenReturn(userPrincipal);
        user = new UserAuth(1L, "test@example.com", "password", "Test User", Set.of(RoleType.ROLE_USER));
    }

    @Test
    void findById_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserAuth foundUser = userService.findById(1L);
        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void findById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> userService.findById(1L));
        assertThat(exception).hasMessage("Пользователь с ID 1 не найден!");
    }

    @Test
    void findByEmail_Success() {
        when(userRepository.findByEmailIgnoreCase("test@example.com")).thenReturn(Optional.of(user));
        UserAuth foundUser = userService.findByEmail("test@example.com");
        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void findByEmail_UserNotFound() {
        when(userRepository.findByEmailIgnoreCase("test@example.com")).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> userService.findByEmail("test@example.com"));
        assertThat(exception).hasMessage("Пользователь с email test@example.com не найден!");
    }

    @Test
    void getCurrentUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserAuth currentUser = userService.getCurrentUser();
        assertThat(currentUser).isEqualTo(user);
    }

    @Test
    void existsByEmail() {
        when(userRepository.existsByEmailIgnoreCase("test@example.com")).thenReturn(true);
        boolean exists = userService.existsByEmail("test@example.com");
        assertThat(exists).isTrue();
    }

    @Test
    void existsByEmail_NotExists() {
        when(userRepository.existsByEmailIgnoreCase("test@example.com")).thenReturn(false);
        boolean exists = userService.existsByEmail("test@example.com");
        assertThat(exists).isFalse();
    }
}