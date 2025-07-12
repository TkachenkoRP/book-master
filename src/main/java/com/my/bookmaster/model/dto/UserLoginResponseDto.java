package com.my.bookmaster.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ при входе пользователя")
public record UserLoginResponseDto(
        @Schema(description = "Access token")
        String accessToken,
        @Schema(description = "Refresh token")
        String refreshToken) {
}
