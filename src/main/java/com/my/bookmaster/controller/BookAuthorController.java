package com.my.bookmaster.controller;

import com.my.bookmaster.annotation.Loggable;
import com.my.bookmaster.controller.doc.BookAuthorControllerDoc;
import com.my.bookmaster.mapper.BookAuthorMapper;
import com.my.bookmaster.model.BookAuthor;
import com.my.bookmaster.model.dto.BookAuthorRequestDto;
import com.my.bookmaster.model.dto.BookAuthorResponseDto;
import com.my.bookmaster.service.BookAuthorService;
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
@RequestMapping("api/book-author")
@RequiredArgsConstructor
@Loggable
@Slf4j
public class BookAuthorController implements BookAuthorControllerDoc {

    private final BookAuthorService bookAuthorService;
    private final BookAuthorMapper bookAuthorMapper;

    @GetMapping
    public List<BookAuthorResponseDto> getAll() {
        List<BookAuthor> bookAuthors = bookAuthorService.getAll();
        List<BookAuthorResponseDto> bookAuthorResponseDtoList = bookAuthorMapper.toDto(bookAuthors);
        log.debug("Get all book authors - {}", bookAuthorResponseDtoList);
        return bookAuthorResponseDtoList;
    }

    @GetMapping("/{id}")
    public BookAuthorResponseDto getById(@PathVariable Long id) {
        BookAuthor bookAuthor = bookAuthorService.getById(id);
        BookAuthorResponseDto bookAuthorResponseDto = bookAuthorMapper.toDto(bookAuthor);
        log.debug("Get book author by id - {}: {}", id, bookAuthorResponseDto);
        return bookAuthorResponseDto;
    }

    @PostMapping
    public BookAuthorResponseDto post(@RequestBody @Valid BookAuthorRequestDto request) {
        BookAuthor newBookAuthor = bookAuthorMapper.toEntity(request);
        BookAuthor saved = bookAuthorService.save(newBookAuthor);
        BookAuthorResponseDto savedDto = bookAuthorMapper.toDto(saved);
        log.debug("Created new book author - {}", savedDto);
        return savedDto;
    }

    @PatchMapping("/{id}")
    public BookAuthorResponseDto patch(@PathVariable Long id,
                                       @RequestBody @Valid BookAuthorRequestDto request) {
        BookAuthor bookAuthorEntity = bookAuthorMapper.toEntity(request);
        BookAuthor patchedBookAuthor = bookAuthorService.patch(id, bookAuthorEntity);
        BookAuthorResponseDto patchedDto = bookAuthorMapper.toDto(patchedBookAuthor);
        log.debug("Patched book author with id - {}: {}", id, patchedDto);
        return patchedDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookAuthorService.delete(id);
        log.debug("Deleted book author with id - {}", id);
    }
}
