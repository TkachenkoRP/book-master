package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с информацией о жанре книги")
public record BookGenreResponseDto(
        @Schema(description = "Идентификатор жанра")
        Long id,
        @Schema(description = "Название жанра книги")
        String genre) {
}