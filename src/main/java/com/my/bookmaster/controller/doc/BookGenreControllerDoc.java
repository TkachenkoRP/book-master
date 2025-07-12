package com.my.bookmaster.controller.doc;

import com.my.bookmaster.model.dto.BookGenreRequestDto;
import com.my.bookmaster.model.dto.BookGenreResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface BookGenreControllerDoc {
    @Operation(summary = "Получить все жанры книг", description = "Возвращает список всех жанров книг.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список жанров успешно получен")
    })
    List<BookGenreResponseDto> getAllBookGenres();

    @Operation(summary = "Получить жанр книги по ID", description = "Возвращает жанр книги по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Жанр успешно найден"),
            @ApiResponse(responseCode = "404", description = "Жанр не найден")
    })
    BookGenreResponseDto getById(@Parameter(description = "ID жанра книги") Long id);

    @Operation(summary = "Добавить новый жанр книги", description = "Создаёт новый жанр книги.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Жанр успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")
    })
    BookGenreResponseDto post(@Parameter(description = "Данные для создания жанра книги") BookGenreRequestDto request);

    @Operation(summary = "Обновить информацию о жанре книги", description = "Обновляет информацию о жанре книги по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Жанр успешно обновлён"),
            @ApiResponse(responseCode = "404", description = "Жанр не найден")
    })
    BookGenreResponseDto patch(@Parameter(description = "ID жанра книги") Long id,
                               @Parameter(description = "Данные для обновления жанра книги") BookGenreRequestDto request);

    @Operation(summary = "Удалить жанр книги", description = "Удаляет жанр книги по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Жанр успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Жанр не найден")
    })
    void delete(@Parameter(description = "ID жанра книги") Long id);
}
