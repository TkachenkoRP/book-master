package com.my.bookmaster.service.impl;

import com.my.bookmaster.exception.EntityNotFoundException;
import com.my.bookmaster.mapper.BookGenreMapper;
import com.my.bookmaster.model.BookGenre;
import com.my.bookmaster.repository.BookGenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookGenreServiceImplTest {

    @Mock
    private BookGenreRepository repository;
    @Mock
    private BookGenreMapper mapper;
    @InjectMocks
    private BookGenreServiceImpl service;
    private BookGenre bookGenre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookGenre = new BookGenre();
        bookGenre.setId(1L);
        bookGenre.setGenre("Фантастика");
    }

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(Collections.singletonList(bookGenre));
        List<BookGenre> genres = service.getAll();
        assertThat(genres).hasSize(1);
        assertThat(genres.get(0).getGenre()).isEqualTo("Фантастика");
        verify(repository, times(1)).findAll();
    }

    @Test
    void getById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(bookGenre));
        BookGenre result = service.getById(1L);
        assertThat(result).isNotNull();
        assertThat(result.getGenre()).isEqualTo("Фантастика");
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void getById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            service.getById(1L);
        });
        assertThat(exception).hasMessage("Жанр книги с ID 1 не найден!");
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(repository.save(bookGenre)).thenReturn(bookGenre);
        BookGenre savedGenre = service.save(bookGenre);
        assertThat(savedGenre).isNotNull();
        assertThat(savedGenre.getGenre()).isEqualTo("Фантастика");
        verify(repository, times(1)).save(bookGenre);
    }

    @Test
    void patch() {
        BookGenre existingGenre = new BookGenre();
        existingGenre.setId(1L);
        existingGenre.setGenre("Научная фантастика");
        when(repository.findById(1L)).thenReturn(Optional.of(existingGenre));
        when(repository.save(existingGenre)).thenReturn(bookGenre);
        doNothing().when(mapper).pathBookGenre(any(), any());
        BookGenre patchedGenre = service.patch(1L, bookGenre);
        assertThat(patchedGenre.getGenre()).isEqualTo(bookGenre.getGenre());
        verify(repository, times(1)).findById(1L);
        verify(mapper, times(1)).pathBookGenre(bookGenre, existingGenre);
        verify(repository, times(1)).save(existingGenre);
    }

    @Test
    void delete() {
        doNothing().when(repository).deleteById(1L);
        assertThatCode(() -> service.delete(1L)).doesNotThrowAnyException();
        verify(repository, times(1)).deleteById(1L);
    }
}