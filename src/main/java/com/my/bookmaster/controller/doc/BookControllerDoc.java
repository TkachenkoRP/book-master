package com.my.bookmaster.controller.doc;

import com.my.bookmaster.model.BookFilter;
import com.my.bookmaster.model.dto.BookRequestDto;
import com.my.bookmaster.model.dto.BookResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@Tag(name = "Book", description = "Контроллер для работы с книгами")
public interface BookControllerDoc {
    @Operation(summary = "Получить все книги", description = "Возвращает список всех книг с применением фильтров.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список книг успешно получен"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные фильтра")
    })
    List<BookResponseDto> getAll(@Parameter(description = "Фильтр для поиска книг") BookFilter filter);

    @Operation(summary = "Получить книгу по ID", description = "Возвращает книгу по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга успешно найдена"),
            @ApiResponse(responseCode = "404", description = "Книга не найдена")
    })
    BookResponseDto getById(@Parameter(description = "ID книги") Long id);

    @Operation(summary = "Добавить новую книгу", description = "Создаёт новую книгу.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Книга успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")
    })
    BookResponseDto post(@Parameter(description = "Данные для создания книги") @Valid BookRequestDto request);

    @Operation(summary = "Обновить информацию о книге", description = "Обновляет информацию о книге по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Книга не найдена")
    })
    BookResponseDto patch(@Parameter(description = "ID книги") Long id,
                          @Parameter(description = "Данные для обновления книги") @Valid BookRequestDto request);

    @Operation(summary = "Удалить книгу", description = "Удаляет книгу по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Книга успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Книга не найдена")
    })
    void deleteById(@Parameter(description = "ID книги") Long id);
}
