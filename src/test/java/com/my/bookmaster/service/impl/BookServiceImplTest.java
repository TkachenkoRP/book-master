package com.my.bookmaster.service.impl;

import com.my.bookmaster.exception.EntityNotFoundException;
import com.my.bookmaster.mapper.BookMapper;
import com.my.bookmaster.model.Book;
import com.my.bookmaster.model.BookFilter;
import com.my.bookmaster.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class BookServiceImplTest {

    @Mock
    private BookRepository repository;
    @Mock
    private BookMapper mapper;
    @InjectMocks
    private BookServiceImpl service;
    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1L);
        book.setTitle("Три мушкетера");
        book.setAuthorId(1L);
        book.setGenreId(2L);
    }

    @Test
    void getAll() {
        BookFilter filter = new BookFilter(null, null, null);
        Book book = new Book();
        when(repository.findAll(any(Specification.class))).thenReturn(Collections.singletonList(book));
        List<Book> result = service.getAll(filter);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.get(0)).isEqualTo(book)
        );
        verify(repository).findAll(any(Specification.class));
    }

    @Test
    void getById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        Book result = service.getById(1L);
        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(book)
        );
        verify(repository).findById(1L);
    }

    @Test
    void getById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            service.getById(1L);
        });
        assertThat(exception).hasMessage("Книга с ID 1 не найдена!");
        verify(repository).findById(1L);
    }

    @Test
    void save() {
        when(repository.save(book)).thenReturn(book);
        Book savedBook = service.save(book);
        assertAll(
                () -> assertThat(savedBook).isNotNull(),
                () -> assertThat(savedBook).isEqualTo(book)
        );
        verify(repository).save(book);
    }

    @Test
    void patch() {
        Book existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitle("Всадник без головы");
        when(repository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(repository.save(existingBook)).thenReturn(book);
        doNothing().when(mapper).pathBook(any(), any());
        Book patchedBook = service.patch(1L, book);
        assertThat(patchedBook.getTitle()).isEqualTo(book.getTitle());
        verify(mapper).pathBook(book, existingBook);
        verify(repository).save(existingBook);
    }

    @Test
    void delete() {
        doNothing().when(repository).deleteById(1L);
        assertThatCode(() -> service.delete(1L)).doesNotThrowAnyException();
        verify(repository, times(1)).deleteById(1L);
    }
}