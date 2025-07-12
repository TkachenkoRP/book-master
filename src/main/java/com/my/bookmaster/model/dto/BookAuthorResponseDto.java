package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с информацией об авторе книги")
public record BookAuthorResponseDto(
        @Schema(description = "Идентификатор автора")
        Long id,
        @Schema(description = "Имя автора книги")
        String name) {
}