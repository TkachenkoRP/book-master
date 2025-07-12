package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Запрос на создание или обновление книги")
public record BookRequestDto(
        @NotBlank(message = "Поле title должно быть заполнено!")
        @Schema(description = "Название книги")
        String title,
        @NotNull(message = "Поле authorId должно быть заполнено!")
        @Positive(message = "Поле authorId должно быть положительным!")
        @Schema(description = "Идентификатор автора книги")
        Long authorId,
        @NotNull(message = "Поле genreId должно быть заполнено!")
        @Positive(message = "Поле genreId должно быть положительным!")
        @Schema(description = "Идентификатор жанра книги")
        Long genreId) {
}