package com.my.bookmaster.mapper;

import com.my.bookmaster.model.Book;
import com.my.bookmaster.model.dto.BookRequestDto;
import com.my.bookmaster.model.dto.BookResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {BookAuthorMap.class, BookAuthorMapper.class, BookGenreMap.class, BookGenreMapper.class})
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void pathBook(Book sourceBook, @MappingTarget Book targetBook);

    @Mapping(target = "author", source = "authorId")
    @Mapping(target = "genre", source = "genreId")
    BookResponseDto toDto(Book entity);

    List<BookResponseDto> toDto(List<Book> entityList);

    Book toEntity(BookRequestDto request);
}
