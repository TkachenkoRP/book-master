package com.my.bookmaster.controller;

import com.my.bookmaster.annotation.Loggable;
import com.my.bookmaster.controller.doc.AuthControllerDoc;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Loggable
@Slf4j
public class AuthController implements AuthControllerDoc {

    private final UserService userService;
    private final SecurityService securityService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRegistrationRequestDto request) {
        if (userService.existsByEmail(request.email())) {
            throw new AlreadyExitsException("Email уже зарегистрирован");
        }
        UserAuth registered = securityService.register(request);
        UserResponseDto userResponseDto = userMapper.toDto(registered);
        log.debug("User register - {}", userResponseDto);
        return userResponseDto;
    }

    @PostMapping("/signin")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto request) {
        UserLoginResponseDto loginResponseDto = securityService.login(request);
        log.debug("User login - {}", loginResponseDto);
        return loginResponseDto;
    }

    @PostMapping("/logout")
    public void logout() {
        securityService.logout();
        log.debug("User logout");
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponseDto refreshToken(@RequestBody RefreshTokenRequestDto request) {
        RefreshTokenResponseDto refreshTokenResponseDto = securityService.refreshToken(request);
        log.debug("Get refresh token - {}", refreshTokenResponseDto);
        return refreshTokenResponseDto;
    }
}
