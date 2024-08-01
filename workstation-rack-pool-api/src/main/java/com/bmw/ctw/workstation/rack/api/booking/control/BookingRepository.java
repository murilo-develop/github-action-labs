package com.bmw.ctw.workstation.rack.api.booking.control;

import com.bmw.ctw.workstation.rack.api.booking.entity.Booking;
import com.bmw.ctw.workstation.rack.api.booking.entity.BookingDTO;
import com.bmw.ctw.workstation.rack.api.booking.entity.BookingMapper;
import com.bmw.ctw.workstation.rack.api.team.entity.TeamMember;
import com.bmw.ctw.workstation.rack.api.workstation.entity.WorkStation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.bmw.ctw.workstation.rack.api.booking.entity.Booking.BOOKING_UPDATE;

@Dependent
public class BookingRepository implements PanacheRepository<Booking> {
    public List<BookingDTO> fetchAllBookings() {
        return listAll()
            .stream()
            .map(BookingMapper.INSTANCE::toBookingDTO)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public BookingDTO fetchBooking(UUID bookingId) {
        return BookingMapper.INSTANCE.toBookingDTO(Booking.findById(bookingId));
    }

    @Transactional
    public BookingDTO insertBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.teamMember = TeamMember.findById(bookingDTO.getTeamMember().getId());
        booking.workStation = WorkStation.findById(bookingDTO.getWorkStation().id);
        booking.bookingFrom = bookingDTO.getBookingFrom();
        booking.bookingTo = bookingDTO.getBookingTo();
        booking.status = bookingDTO.getStatus();

        booking.persist();

        return BookingMapper.INSTANCE.toBookingDTO(booking);
    }

    @Transactional
    public void updateBooking(UUID bookingId, BookingDTO bookingDTO) {
        TeamMember teamMember = TeamMember.findById(bookingDTO.getTeamMember().getId());
        WorkStation workStation = WorkStation.findById(bookingDTO.getWorkStation().id);

        update("#" + BOOKING_UPDATE, Map.of(
            "teamMember", teamMember,
            "workStation", workStation,
            "bookingFrom", bookingDTO.getBookingFrom(),
            "bookingTo", bookingDTO.getBookingTo(),
            "status", bookingDTO.getStatus(),
            "id", bookingId)
        );
    }

    @Transactional
    public void deleteBooking(UUID bookingId) {
        delete("id", bookingId);
    }
}
