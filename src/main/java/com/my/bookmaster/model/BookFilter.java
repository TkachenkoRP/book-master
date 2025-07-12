package com.my.bookmaster.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Фильтр для поиска книг")
public record BookFilter(
        @Schema(description = "Название книги")
        String title,
        @Schema(description = "Автор книги")
        String author,
        @Schema(description = "Жанр книги")
        String genre) {
}
