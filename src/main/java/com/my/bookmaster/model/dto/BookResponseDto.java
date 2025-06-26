package com.my.bookmaster.model.dto;

public record BookResponseDto(Long id, String title, BookAuthorResponseDto author, BookGenreResponseDto genre) {
}