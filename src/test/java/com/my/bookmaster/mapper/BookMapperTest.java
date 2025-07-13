package com.my.bookmaster.mapper;

import com.my.bookmaster.model.Book;
import com.my.bookmaster.model.BookAuthor;
import com.my.bookmaster.model.BookGenre;
import com.my.bookmaster.model.dto.BookAuthorResponseDto;
import com.my.bookmaster.model.dto.BookGenreResponseDto;
import com.my.bookmaster.model.dto.BookRequestDto;
import com.my.bookmaster.model.dto.BookResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookMapperTest {
    @InjectMocks
    private BookMapperImpl bookMapper;
    @Mock
    private BookAuthorMapperImpl bookAuthorMapper;
    @Mock
    private BookGenreMapperImpl bookGenreMapper;
    @Mock
    private BookAuthorMap bookAuthorMap;
    @Mock
    private BookGenreMap bookGenreMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void pathBook() {
        Book source = new Book();
        source.setTitle("Война и мир");
        source.setAuthorId(1L);
        source.setGenreId(2L);
        Book target = new Book();
        target.setId(10L);
        bookMapper.pathBook(source, target);
        assertThat(target.getTitle()).isEqualTo("Война и мир");
        assertThat(target.getAuthorId()).isEqualTo(1L);
        assertThat(target.getGenreId()).isEqualTo(2L);
        assertThat(target.getId()).isEqualTo(10L);
    }

    @Test
    void toDto() {
        Book entity = new Book();
        entity.setId(1L);
        entity.setTitle("Война и мир");
        entity.setAuthorId(2L);
        entity.setGenreId(3L);
        when(bookAuthorMapper.toDto(any(BookAuthor.class))).thenReturn(new BookAuthorResponseDto(2L, "Лев Толстой"));
        when(bookGenreMapper.toDto(any(BookGenre.class))).thenReturn(new BookGenreResponseDto(3L, "Роман"));
        when(bookAuthorMap.fromId(2L)).thenReturn(new BookAuthor());
        when(bookGenreMap.fromId(3L)).thenReturn(new BookGenre());
        BookResponseDto dto = bookMapper.toDto(entity);
        assertThat(dto).isNotNull();
        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.title()).isEqualTo("Война и мир");
        assertThat(dto.author()).isNotNull();
        assertThat(dto.genre()).isNotNull();
    }

    @Test
    void testToListDto() {
        Book entity1 = new Book();
        entity1.setId(1L);
        entity1.setTitle("Война и мир");
        entity1.setAuthorId(2L);
        entity1.setGenreId(3L);
        Book entity2 = new Book();
        entity2.setId(2L);
        entity2.setTitle("Человек амфибия");
        entity2.setAuthorId(4L);
        entity2.setGenreId(5L);
        when(bookAuthorMap.fromId(2L)).thenReturn(new BookAuthor());
        when(bookGenreMap.fromId(3L)).thenReturn(new BookGenre());
        when(bookAuthorMap.fromId(4L)).thenReturn(new BookAuthor());
        when(bookGenreMap.fromId(5L)).thenReturn(new BookGenre());
        List<Book> entityList = List.of(entity1, entity2);
        List<BookResponseDto> dtoList = bookMapper.toDto(entityList);
        assertThat(dtoList).hasSize(2);
        assertThat(dtoList.get(0).title()).isEqualTo("Война и мир");
        assertThat(dtoList.get(1).title()).isEqualTo("Человек амфибия");
    }

    @Test
    void toEntity() {
        BookRequestDto requestDto = new BookRequestDto("Новинка", 1L, 2L);
        Book entity = bookMapper.toEntity(requestDto);
        assertThat(entity).isNotNull();
        assertThat(entity.getTitle()).isEqualTo("Новинка");
        assertThat(entity.getAuthorId()).isEqualTo(1L);
        assertThat(entity.getGenreId()).isEqualTo(2L);
        assertThat(entity.getId()).isNull();
    }
}