package com.bmw.ctw.workstation.rack.api.booking.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target = "getId", source = "id")
    @Mapping(target = "getTeamMember.getId", source = "teamMember.id")
    @Mapping(target = "getTeamMember.getCtwCode", source = "teamMember.ctwCode")
    @Mapping(target = "getTeamMember.getName", source = "teamMember.name")
    @Mapping(target = "getTeamMember.getTeamDTO.getId", source = "teamMember.team.id")
    @Mapping(target = "getTeamMember.getTeamDTO.getName", source = "teamMember.team.name")
    @Mapping(target = "getTeamMember.getTeamDTO.getProduct", source = "teamMember.team.product")
    @Mapping(target = "getWorkStation", source = "workStation")
    @Mapping(target = "getBookingFrom", source = "bookingFrom")
    @Mapping(target = "getBookingTo", source = "bookingTo")
    @Mapping(target = "getStatus", source = "status")
    BookingDTO toBookingDTO(Booking booking);
}
