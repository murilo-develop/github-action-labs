package com.bmw.ctw.workstation.rack.api.booking.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "teamMember.id", source = "teamMember.id")
    @Mapping(target = "teamMember.ctwCode", source = "teamMember.ctwCode")
    @Mapping(target = "teamMember.name", source = "teamMember.name")
    @Mapping(target = "teamMember.team.id", source = "teamMember.team.id")
    @Mapping(target = "teamMember.team.name", source = "teamMember.team.name")
    @Mapping(target = "teamMember.team.product", source = "teamMember.team.product")
    @Mapping(target = "workStation", source = "workStation")
    @Mapping(target = "bookingFrom", source = "bookingFrom")
    @Mapping(target = "bookingTo", source = "bookingTo")
    @Mapping(target = "status", source = "status")
    BookingDTO toBookingDTO(Booking booking);
}
