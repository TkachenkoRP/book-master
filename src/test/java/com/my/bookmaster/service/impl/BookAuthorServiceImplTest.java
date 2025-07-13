package com.my.bookmaster.service.impl;

import com.my.bookmaster.exception.EntityNotFoundException;
import com.my.bookmaster.mapper.BookAuthorMapper;
import com.my.bookmaster.model.BookAuthor;
import com.my.bookmaster.repository.BookAuthorRepository;
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

class BookAuthorServiceImplTest {
    @Mock
    private BookAuthorRepository repository;
    @Mock
    private BookAuthorMapper mapper;
    @InjectMocks
    private BookAuthorServiceImpl service;
    private BookAuthor bookAuthor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookAuthor = new BookAuthor();
        bookAuthor.setId(1L);
        bookAuthor.setName("Автор 1");
    }

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(Collections.singletonList(bookAuthor));
        List<BookAuthor> authors = service.getAll();
        assertThat(authors).hasSize(1);
        assertThat(authors.get(0)).isEqualTo(bookAuthor);
        verify(repository, times(1)).findAll();
    }

    @Test
    void getById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(bookAuthor));
        BookAuthor author = service.getById(1L);
        assertThat(author).isEqualTo(bookAuthor);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void getById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> service.getById(1L));
        assertThat(exception).hasMessage("Автор с ID 1 не найден!");
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(repository.save(bookAuthor)).thenReturn(bookAuthor);
        BookAuthor savedAuthor = service.save(bookAuthor);
        assertThat(savedAuthor).isEqualTo(bookAuthor);
        verify(repository, times(1)).save(bookAuthor);
    }

    @Test
    void patch() {
        BookAuthor existingAuthor = new BookAuthor();
        existingAuthor.setId(1L);
        existingAuthor.setName("Старый Автор");
        when(repository.findById(1L)).thenReturn(Optional.of(existingAuthor));
        when(repository.save(existingAuthor)).thenReturn(bookAuthor);
        doNothing().when(mapper).pathBookAuthor(any(), any());
        BookAuthor patchedAuthor = service.patch(1L, bookAuthor);
        assertThat(patchedAuthor.getName()).isEqualTo(bookAuthor.getName());
        verify(repository, times(1)).findById(1L);
        verify(mapper, times(1)).pathBookAuthor(bookAuthor, existingAuthor);
        verify(repository, times(1)).save(existingAuthor);
    }

    @Test
    void delete() {
        doNothing().when(repository).deleteById(1L);
        assertThatCode(() -> service.delete(1L)).doesNotThrowAnyException();
        verify(repository, times(1)).deleteById(1L);
    }
}