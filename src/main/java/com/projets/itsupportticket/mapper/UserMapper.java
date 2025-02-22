package com.projets.itsupportticket.mapper;


import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.dto.UserCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface UserMapper {
    User toEntity(UserCreateDto userCreateDto);
}
