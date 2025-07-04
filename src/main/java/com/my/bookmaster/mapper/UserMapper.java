package com.my.bookmaster.mapper;

import com.my.bookmaster.model.UserAuth;
import com.my.bookmaster.model.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponseDto toDto(UserAuth entity);
}
