package com.my.bookmaster.service;

import com.my.bookmaster.model.UserAuth;

public interface UserService {
    UserAuth findById(Long id);
    UserAuth findByEmail(String email);
    UserAuth getCurrentUser();
    boolean existsByEmail(String email);
}
