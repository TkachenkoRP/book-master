package com.my.bookmaster.controller.doc;

import com.my.bookmaster.model.dto.RefreshTokenRequestDto;
import com.my.bookmaster.model.dto.RefreshTokenResponseDto;
import com.my.bookmaster.model.dto.UserLoginRequestDto;
import com.my.bookmaster.model.dto.UserLoginResponseDto;
import com.my.bookmaster.model.dto.UserRegistrationRequestDto;
import com.my.bookmaster.model.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth controller", description = "Контроллер для работы с аутентификацией пользователей")
public interface AuthControllerDoc {
    @Operation(summary = "Регистрация пользователя", description = "Создаёт нового пользователя с указанным email и паролем.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Email уже зарегистрирован")
    })
    UserResponseDto register(@Parameter(description = "Данные для регистрации пользователя") @RequestBody UserRegistrationRequestDto request);

    @Operation(summary = "Вход пользователя", description = "Авторизует пользователя и возвращает JWT токен.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно вошёл"),
            @ApiResponse(responseCode = "401", description = "Неверные учётные данные")
    })
    UserLoginResponseDto login(@Parameter(description = "Данные для входа пользователя") @RequestBody UserLoginRequestDto request);

    @Operation(summary = "Выход пользователя", description = "Выходит пользователя из системы.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно вышел")
    })
    void logout();

    @Operation(summary = "Обновление токена", description = "Обновляет рефреш-токен и возвращает новый JWT токен.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Токен успешно обновлён"),
            @ApiResponse(responseCode = "400", description = "Неверный рефреш-токен")
    })
    RefreshTokenResponseDto refreshToken(@Parameter(description = "Данные для обновления токена") @RequestBody RefreshTokenRequestDto request);
}
