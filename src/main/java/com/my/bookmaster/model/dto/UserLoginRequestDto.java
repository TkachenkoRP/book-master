package com.my.bookmaster.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @Email(message = "Не верно указан email!") String email,
        @NotBlank(message = "Укажите пароль пользователя!") String password) {
}
