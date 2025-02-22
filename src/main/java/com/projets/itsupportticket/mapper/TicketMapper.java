package com.projets.itsupportticket.mapper;


import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.dto.TicketCreateDto;
import com.projets.itsupportticket.mapper.helper.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {UserMapperHelper.class})
public interface TicketMapper {

    @Mapping(source = "userId", target = "user")
    Ticket toEntity(TicketCreateDto ticketCreateDto);

}
