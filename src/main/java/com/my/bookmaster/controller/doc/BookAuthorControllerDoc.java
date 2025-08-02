package com.my.bookmaster.controller.doc;

import com.my.bookmaster.model.dto.BookAuthorRequestDto;
import com.my.bookmaster.model.dto.BookAuthorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "BookAuthorController", description = "Контроллер для управления авторами книг")
public interface BookAuthorControllerDoc {
    @Operation(summary = "Получить всех авторов книг", description = "Возвращает список всех авторов книг.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список авторов успешно получен")
    })
    List<BookAuthorResponseDto> getAll();

    @Operation(summary = "Получить автора книги по ID", description = "Возвращает автора книги по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Автор успешно найден"),
            @ApiResponse(responseCode = "404", description = "Автор не найден")
    })
    BookAuthorResponseDto getById(@Parameter(description = "ID автора книги") Long id);

    @Operation(summary = "Добавить нового автора книги", description = "Создаёт нового автора книги.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Автор успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")
    })
    BookAuthorResponseDto post(@Parameter(description = "Данные для создания автора книги") BookAuthorRequestDto request);

    @Operation(summary = "Обновить информацию об авторе книги", description = "Обновляет информацию об авторе книги по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Автор успешно обновлён"),
            @ApiResponse(responseCode = "404", description = "Автор не найден")
    })
    BookAuthorResponseDto patch(@Parameter(description = "ID автора книги") Long id,
                                @Parameter(description = "Данные для обновления автора книги") BookAuthorRequestDto request);

    @Operation(summary = "Удалить автора книги", description = "Удаляет автора книги по указанному ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Автор успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Автор не найден")
    })
    void delete(@Parameter(description = "ID автора книги") Long id);
}
