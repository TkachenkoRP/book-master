package com.my.bookmaster.mapper;

import com.my.bookmaster.model.BookGenre;
import com.my.bookmaster.model.dto.BookGenreRequestDto;
import com.my.bookmaster.model.dto.BookGenreResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookGenreMapperTest {

    private BookGenreMapper bookGenreMapper;

    @BeforeEach
    void setUp() {
        bookGenreMapper = Mappers.getMapper(BookGenreMapper.class);
    }

    @Test
    void pathBookGenre() {
        BookGenre source = new BookGenre();
        source.setGenre("Фантастика");
        BookGenre target = new BookGenre();
        target.setId(1L);
        bookGenreMapper.pathBookGenre(source, target);
        assertThat(target.getGenre()).isEqualTo("Фантастика");
        assertThat(target.getId()).isEqualTo(1L);
    }

    @Test
    void toDto() {
        BookGenre entity = new BookGenre();
        entity.setId(1L);
        entity.setGenre("Фантастика");
        BookGenreResponseDto dto = bookGenreMapper.toDto(entity);
        assertThat(dto).isNotNull();
        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.genre()).isEqualTo("Фантастика");
    }

    @Test
    void testToListDto() {
        BookGenre entity1 = new BookGenre();
        entity1.setId(1L);
        entity1.setGenre("Фантастика");
        BookGenre entity2 = new BookGenre();
        entity2.setId(2L);
        entity2.setGenre("Детектив");
        List<BookGenre> entityList = List.of(entity1, entity2);
        List<BookGenreResponseDto> dtoList = bookGenreMapper.toDto(entityList);
        assertThat(dtoList).hasSize(2);
        assertThat(dtoList.get(0).genre()).isEqualTo("Фантастика");
        assertThat(dtoList.get(1).genre()).isEqualTo("Детектив");
    }

    @Test
    void toEntity() {
        BookGenreRequestDto requestDto = new BookGenreRequestDto("Новинка");
        BookGenre entity = bookGenreMapper.toEntity(requestDto);
        assertThat(entity).isNotNull();
        assertThat(entity.getGenre()).isEqualTo("Новинка");
        assertThat(entity.getId()).isNull();
    }
}