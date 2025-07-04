package com.my.bookmaster.controller;

import com.my.bookmaster.annotation.Loggable;
import com.my.bookmaster.exception.AlreadyExitsException;
import com.my.bookmaster.mapper.UserMapper;
import com.my.bookmaster.model.UserAuth;
import com.my.bookmaster.model.dto.RefreshTokenRequestDto;
import com.my.bookmaster.model.dto.RefreshTokenResponseDto;
import com.my.bookmaster.model.dto.UserLoginRequestDto;
import com.my.bookmaster.model.dto.UserLoginResponseDto;
import com.my.bookmaster.model.dto.UserRegistrationRequestDto;
import com.my.bookmaster.model.dto.UserResponseDto;
import com.my.bookmaster.security.SecurityService;
import com.my.bookmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Loggable
public class AuthController {

    private final UserService userService;
    private final SecurityService securityService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRegistrationRequestDto request) {
        if (userService.existsByEmail(request.email())) {
            throw new AlreadyExitsException("Email уже зарегистрирован");
        }
        UserAuth registered = securityService.register(request);
        return userMapper.toDto(registered);
    }

    @PostMapping("/signin")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto request) {
        return securityService.login(request);
    }

    @PostMapping("/logout")
    public void logout() {
        securityService.logout();
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponseDto refreshToken(@RequestBody RefreshTokenRequestDto request) {
        return securityService.refreshToken(request);
    }
}
