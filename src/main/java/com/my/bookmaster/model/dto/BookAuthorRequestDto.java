package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на создание или обновление автора книги")
public record BookAuthorRequestDto(
        @NotBlank(message = "Поле name должно быть заполнено!")
        @Schema(description = "Имя автора книги")
        String name) {
}