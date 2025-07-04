package com.my.bookmaster.model.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDto(@NotBlank(message = "Укажите refresh token") String refreshToken) {
}
