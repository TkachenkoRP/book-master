package com.my.bookmaster.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegistrationRequestDto(
        @Email(message = "Не верно указан email!") String email,
        @NotBlank(message = "Поле password должно быть заполнено!") String password,
        @NotBlank(message = "Поле name должно быть заполнено!") String name) {
}
