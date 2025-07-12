package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на регистрацию пользователя")
public record UserRegistrationRequestDto(
        @Email(message = "Не верно указан email!")
        @Schema(description = "Email пользователя")
        String email,
        @NotBlank(message = "Поле password должно быть заполнено!")
        @Schema(description = "Пароль пользователя")
        String password,
        @NotBlank(message = "Поле name должно быть заполнено!")
        @Schema(description = "Имя пользователя")
        String name) {
}
