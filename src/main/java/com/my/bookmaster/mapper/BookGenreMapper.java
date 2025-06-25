package com.my.bookmaster.mapper;

import com.my.bookmaster.model.BookGenre;
import com.my.bookmaster.model.dto.BookGenreRequestDto;
import com.my.bookmaster.model.dto.BookGenreResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookGenreMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void pathBookGenre(BookGenre sourceBookGenre, @MappingTarget BookGenre targetBookGenre);

    BookGenreResponseDto toDto(BookGenre entity);

    List<BookGenreResponseDto> toDto(List<BookGenre> entityList);

    BookGenre toEntity(BookGenreRequestDto request);
}
