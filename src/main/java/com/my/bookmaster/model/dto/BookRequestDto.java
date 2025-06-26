package com.my.bookmaster.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookRequestDto(@NotBlank(message = "Поле title должно быть заполнено!") String title,
                             @NotNull(message = "Поле authorId должно быть заполнено!")
                             @Positive(message = "Поле authorId должно быть положительным!") Long authorId,
                             @NotNull(message = "Поле genreId должно быть заполнено!")
                             @Positive(message = "Поле genreId должно быть положительным!") Long genreId) {
}