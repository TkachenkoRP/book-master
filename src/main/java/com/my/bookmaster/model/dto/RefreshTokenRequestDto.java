package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на обновление токена")
public record RefreshTokenRequestDto(
        @NotBlank(message = "Укажите refresh token")
        @Schema(description = "Refresh token")
        String refreshToken) {
}
