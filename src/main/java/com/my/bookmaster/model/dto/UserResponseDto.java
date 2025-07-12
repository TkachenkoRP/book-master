package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с информацией о пользователе")
public record UserResponseDto(
        @Schema(description = "Идентификатор пользователя")
        Long id,
        @Schema(description = "Email пользователя")
        String email,
        @Schema(description = "Имя пользователя")
        String name) {
}
