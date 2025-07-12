package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на создание или обновление жанра книги")
public record BookGenreRequestDto(
        @NotBlank(message = "Поле genre должно быть заполнено!")
        @Schema(description = "Название жанра книги")
        String genre) {
}