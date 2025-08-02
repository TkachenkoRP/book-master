package com.my.bookmaster.controller;

import com.my.bookmaster.annotation.Loggable;
import com.my.bookmaster.controller.doc.BookGenreControllerDoc;
import com.my.bookmaster.mapper.BookGenreMapper;
import com.my.bookmaster.model.BookGenre;
import com.my.bookmaster.model.dto.BookGenreRequestDto;
import com.my.bookmaster.model.dto.BookGenreResponseDto;
import com.my.bookmaster.service.BookGenreService;
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
@RequestMapping("api/book-genre")
@RequiredArgsConstructor
@Loggable
@Slf4j
public class BookGenreController implements BookGenreControllerDoc {

    private final BookGenreService bookGenreService;
    private final BookGenreMapper bookGenreMapper;

    @GetMapping
    public List<BookGenreResponseDto> getAllBookGenres() {
        List<BookGenre> genreList = bookGenreService.getAll();
        List<BookGenreResponseDto> genreResponseDtos = bookGenreMapper.toDto(genreList);
        log.debug("Get all book genres - {}", genreResponseDtos);
        return genreResponseDtos;
    }

    @GetMapping("/{id}")
    public BookGenreResponseDto getById(@PathVariable Long id) {
        BookGenre bookGenre = bookGenreService.getById(id);
        BookGenreResponseDto genreResponseDto = bookGenreMapper.toDto(bookGenre);
        log.debug("Get book genre by id - {}: {}", id, genreResponseDto);
        return genreResponseDto;
    }

    @PostMapping
    public BookGenreResponseDto post(@RequestBody @Valid BookGenreRequestDto request) {
        BookGenre newBookGenre = bookGenreMapper.toEntity(request);
        BookGenre saved = bookGenreService.save(newBookGenre);
        BookGenreResponseDto savedDto = bookGenreMapper.toDto(saved);
        log.debug("Created new book genre - {}", savedDto);
        return savedDto;
    }

    @PatchMapping("/{id}")
    public BookGenreResponseDto patch(@PathVariable Long id,
                                      @RequestBody @Valid BookGenreRequestDto request) {
        BookGenre bookGenreEntity = bookGenreMapper.toEntity(request);
        BookGenre patchedBookGenre = bookGenreService.patch(id, bookGenreEntity);
        BookGenreResponseDto patchedDto = bookGenreMapper.toDto(patchedBookGenre);
        log.debug("Patched book genre with id - {}: {}", id, patchedDto);
        return patchedDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookGenreService.delete(id);
        log.debug("Deleted book genre with id - {}", id);
    }
}
