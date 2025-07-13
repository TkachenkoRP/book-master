package com.my.bookmaster.mapper;

import com.my.bookmaster.model.BookAuthor;
import com.my.bookmaster.model.dto.BookAuthorRequestDto;
import com.my.bookmaster.model.dto.BookAuthorResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookAuthorMapperTest {
    private BookAuthorMapper bookAuthorMapper;

    @BeforeEach
    void setUp() {
        bookAuthorMapper = Mappers.getMapper(BookAuthorMapper.class);
    }

    @Test
    void pathBookAuthor() {
        BookAuthor source = new BookAuthor();
        source.setName("Author Name");
        source.setId(1L);
        BookAuthor target = new BookAuthor();
        target.setId(2L);
        bookAuthorMapper.pathBookAuthor(source, target);
        assertThat(target.getName()).isEqualTo("Author Name");
        assertThat(target.getId()).isNotEqualTo(1L);
    }

    @Test
    void toDto() {
        BookAuthor entity = new BookAuthor();
        entity.setId(1L);
        entity.setName("Author Name");
        BookAuthorResponseDto dto = bookAuthorMapper.toDto(entity);
        assertThat(dto).isNotNull();
        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.name()).isEqualTo("Author Name");
    }

    @Test
    void testToListDto() {
        BookAuthor entity1 = new BookAuthor();
        entity1.setId(1L);
        entity1.setName("Author One");
        BookAuthor entity2 = new BookAuthor();
        entity2.setId(2L);
        entity2.setName("Author Two");
        List<BookAuthor> entityList = List.of(entity1, entity2);
        List<BookAuthorResponseDto> dtoList = bookAuthorMapper.toDto(entityList);
        assertThat(dtoList).hasSize(2);
        assertThat(dtoList.get(0).name()).isEqualTo("Author One");
        assertThat(dtoList.get(1).name()).isEqualTo("Author Two");
    }

    @Test
    void toEntity() {
        BookAuthorRequestDto requestDto = new BookAuthorRequestDto("New Author");
        BookAuthor entity = bookAuthorMapper.toEntity(requestDto);
        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo("New Author");
        assertThat(entity.getId()).isNull();
    }
}