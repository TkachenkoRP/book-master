package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с информацией о книге")
public record BookResponseDto(
        @Schema(description = "Идентификатор книги")
        Long id,
        @Schema(description = "Название книги")
        String title,
        @Schema(description = "Информация об авторе книги")
        BookAuthorResponseDto author,
        @Schema(description = "Информация о жанре книги")
        BookGenreResponseDto genre) {
}