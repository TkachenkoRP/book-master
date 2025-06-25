package com.my.bookmaster.model.dto;

import jakarta.validation.constraints.NotBlank;

public record BookAuthorRequestDto(
        @NotBlank(message = "Поле name должно быть заполнено!") String name) {
}