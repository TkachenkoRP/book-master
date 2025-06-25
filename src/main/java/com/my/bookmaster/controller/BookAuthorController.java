package com.my.bookmaster.controller;

import com.my.bookmaster.mapper.BookAuthorMapper;
import com.my.bookmaster.model.BookAuthor;
import com.my.bookmaster.model.dto.BookAuthorRequestDto;
import com.my.bookmaster.model.dto.BookAuthorResponseDto;
import com.my.bookmaster.service.BookAuthorService;
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
@RequestMapping("api/book-author")
@RequiredArgsConstructor
public class BookAuthorController {

    private final BookAuthorService bookAuthorService;
    private final BookAuthorMapper bookAuthorMapper;

    @GetMapping
    public List<BookAuthorResponseDto> getAll() {
        List<BookAuthor> bookAuthors = bookAuthorService.getAll();
        return bookAuthorMapper.toDto(bookAuthors);
    }

    @GetMapping("/{id}")
    public BookAuthorResponseDto getById(@PathVariable Long id) {
        BookAuthor bookAuthor = bookAuthorService.getById(id);
        return bookAuthorMapper.toDto(bookAuthor);
    }

    @PostMapping
    public BookAuthorResponseDto post(@RequestBody @Valid BookAuthorRequestDto request) {
        BookAuthor newBookAuthor = bookAuthorMapper.toEntity(request);
        BookAuthor saved = bookAuthorService.save(newBookAuthor);
        return bookAuthorMapper.toDto(saved);
    }

    @PatchMapping("/{id}")
    public BookAuthorResponseDto path(@PathVariable Long id,
                                      @RequestBody @Valid BookAuthorRequestDto request) {
        BookAuthor bookAuthorEntity = bookAuthorMapper.toEntity(request);
        BookAuthor patchedBookAuthor = bookAuthorService.patch(id, bookAuthorEntity);
        return bookAuthorMapper.toDto(patchedBookAuthor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookAuthorService.delete(id);
    }
}
