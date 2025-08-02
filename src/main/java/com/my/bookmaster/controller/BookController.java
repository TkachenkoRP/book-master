package com.my.bookmaster.controller;

import com.my.bookmaster.annotation.Loggable;
import com.my.bookmaster.controller.doc.BookControllerDoc;
import com.my.bookmaster.mapper.BookMapper;
import com.my.bookmaster.model.Book;
import com.my.bookmaster.model.BookFilter;
import com.my.bookmaster.model.dto.BookRequestDto;
import com.my.bookmaster.model.dto.BookResponseDto;
import com.my.bookmaster.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/book")
@RequiredArgsConstructor
@Loggable
@Slf4j
public class BookController implements BookControllerDoc {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookResponseDto> getAll(BookFilter filter) {
        List<Book> books = bookService.getAll(filter);
        List<BookResponseDto> bookResponseDtos = bookMapper.toDto(books);
        log.debug("Get all books - {}", bookResponseDtos);
        return bookResponseDtos;
    }

    @GetMapping("/{id}")
    public BookResponseDto getById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        BookResponseDto bookResponseDto = bookMapper.toDto(book);
        log.debug("Get book by id - {}: {}", id, bookResponseDto);
        return bookResponseDto;
    }

    @PostMapping
    public BookResponseDto post(@RequestBody @Valid BookRequestDto request) {
        Book newBook = bookMapper.toEntity(request);
        Book saved = bookService.save(newBook);
        BookResponseDto savedDto = bookMapper.toDto(saved);
        log.debug("Created new book - {}", savedDto);
        return savedDto;
    }

    @PatchMapping("/{id}")
    public BookResponseDto patch(@PathVariable Long id,
                                 @RequestBody @Valid BookRequestDto request) {
        Book bookEntity = bookMapper.toEntity(request);
        Book patchedBook = bookService.patch(id, bookEntity);
        BookResponseDto patchedDto = bookMapper.toDto(patchedBook);
        log.debug("Patched book with id - {}: {}", id, patchedDto);
        return patchedDto;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.delete(id);
        log.debug("Deleted book with id - {}", id);
    }
}
