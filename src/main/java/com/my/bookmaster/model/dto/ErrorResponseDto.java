package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с информацией об ошибке")
public record ErrorResponseDto(
        @Schema(description = "Сообщение об ошибке")
        String message) {
}
