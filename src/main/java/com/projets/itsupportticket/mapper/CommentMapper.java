package com.projets.itsupportticket.mapper;


import com.projets.itsupportticket.domain.Comment;
import com.projets.itsupportticket.dto.CommentCreateDto;
import com.projets.itsupportticket.mapper.helper.TicketMapperHelper;
import com.projets.itsupportticket.mapper.helper.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapperHelper.class , TicketMapperHelper.class})
public interface CommentMapper {

    @Mapping(source = "userId", target = "author")
    @Mapping(source = "ticketId", target = "ticket")
    Comment toEntity(CommentCreateDto commentCreateDto);
}
