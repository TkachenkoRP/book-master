package com.my.bookmaster.mapper;

import com.my.bookmaster.model.BookAuthor;
import com.my.bookmaster.model.dto.BookAuthorRequestDto;
import com.my.bookmaster.model.dto.BookAuthorResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookAuthorMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void pathBookAuthor(BookAuthor sourceBookAuthor, @MappingTarget BookAuthor targetBookAuthor);

    BookAuthorResponseDto toDto(BookAuthor entity);

    List<BookAuthorResponseDto> toDto(List<BookAuthor> entityList);

    BookAuthor toEntity(BookAuthorRequestDto request);
}
