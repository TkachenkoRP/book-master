package com.my.bookmaster.model.dto;

import jakarta.validation.constraints.NotBlank;

public record BookGenreRequestDto(
        @NotBlank(message = "Поле genre должно быть заполнено!") String genre) {
}