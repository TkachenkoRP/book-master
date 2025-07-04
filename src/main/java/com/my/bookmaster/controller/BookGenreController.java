package com.my.bookmaster.controller;

import com.my.bookmaster.annotation.Loggable;
import com.my.bookmaster.mapper.BookGenreMapper;
import com.my.bookmaster.model.BookGenre;
import com.my.bookmaster.model.dto.BookGenreRequestDto;
import com.my.bookmaster.model.dto.BookGenreResponseDto;
import com.my.bookmaster.service.BookGenreService;
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
@RequestMapping("api/book-genre")
@RequiredArgsConstructor
@Loggable
public class BookGenreController {

    private final BookGenreService bookGenreService;
    private final BookGenreMapper bookGenreMapper;

    @GetMapping
    public List<BookGenreResponseDto> getAllBookGenres() {
        List<BookGenre> genreList = bookGenreService.getAll();
        return bookGenreMapper.toDto(genreList);
    }

    @GetMapping("/{id}")
    public BookGenreResponseDto getById(@PathVariable Long id) {
        BookGenre bookGenre = bookGenreService.getById(id);
        return bookGenreMapper.toDto(bookGenre);
    }

    @PostMapping
    public BookGenreResponseDto post(@RequestBody @Valid BookGenreRequestDto request) {
        BookGenre newBookGenre = bookGenreMapper.toEntity(request);
        BookGenre saved = bookGenreService.save(newBookGenre);
        return bookGenreMapper.toDto(saved);
    }

    @PatchMapping("/{id}")
    public BookGenreResponseDto patch(@PathVariable Long id,
                                      @RequestBody @Valid BookGenreRequestDto request) {
        BookGenre bookGenreEntity = bookGenreMapper.toEntity(request);
        BookGenre patchedBookGenre = bookGenreService.patch(id, bookGenreEntity);
        return bookGenreMapper.toDto(patchedBookGenre);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bookGenreService.delete(id);
    }
}
