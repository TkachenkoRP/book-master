package com.my.bookmaster.service.impl;

import com.my.bookmaster.exception.EntityNotFoundException;
import com.my.bookmaster.model.UserAuth;
import com.my.bookmaster.repository.UserRepository;
import com.my.bookmaster.security.AppUserPrincipal;
import com.my.bookmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public UserAuth findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Пользователь с ID {0} не найден!", id
        )));
    }

    @Override
    public UserAuth findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Пользователь с email {0} не найден!", email
        )));
    }

    @Override
    public UserAuth getCurrentUser() {
        AppUserPrincipal userPrincipal = (AppUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findById(userPrincipal.getId());
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmailIgnoreCase(email);
    }
}
