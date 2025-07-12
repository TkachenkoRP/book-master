package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на вход пользователя")
public record UserLoginRequestDto(
        @Email(message = "Не верно указан email!")
        @Schema(description = "Email пользователя")
        String email,
        @NotBlank(message = "Укажите пароль пользователя!")
        @Schema(description = "Пароль пользователя")
        String password) {
}
