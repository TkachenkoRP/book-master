package com.my.bookmaster.controller;

import com.my.bookmaster.annotation.Loggable;
import com.my.bookmaster.mapper.BookMapper;
import com.my.bookmaster.model.Book;
import com.my.bookmaster.model.BookFilter;
import com.my.bookmaster.model.dto.BookRequestDto;
import com.my.bookmaster.model.dto.BookResponseDto;
import com.my.bookmaster.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookResponseDto> getAll(BookFilter filter) {
        List<Book> books = bookService.getAll(filter);
        return bookMapper.toDto(books);
    }

    @GetMapping("/{id}")
    public BookResponseDto getById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        return bookMapper.toDto(book);
    }

    @PostMapping
    public BookResponseDto post(@RequestBody @Valid BookRequestDto request) {
        Book newBook = bookMapper.toEntity(request);
        Book saved = bookService.save(newBook);
        return bookMapper.toDto(saved);
    }

    @PatchMapping("/{id}")
    public BookResponseDto patch(@PathVariable Long id,
                                 @RequestBody @Valid BookRequestDto request) {
        Book bookEntity = bookMapper.toEntity(request);
        Book patchedBook = bookService.patch(id, bookEntity);
        return bookMapper.toDto(patchedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.delete(id);
    }
}
