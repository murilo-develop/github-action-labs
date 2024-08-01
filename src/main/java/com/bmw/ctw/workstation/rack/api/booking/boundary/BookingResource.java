package com.bmw.ctw.workstation.rack.api.booking.boundary;

import com.bmw.ctw.workstation.rack.api.booking.control.BookingRepository;
import com.bmw.ctw.workstation.rack.api.booking.entity.BookingDTO;
import com.bmw.ctw.workstation.rack.api.infrastructure.constants.boundary.Routes;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Path(Routes.BOOKING)
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {
    private final BookingRepository bookingRepository;

    @Inject
    public BookingResource(final BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GET
    public List<BookingDTO> getAllBookings() {
        return this.bookingRepository.fetchAllBookings();
    }

    @GET
    @Path("/{bookingId}")
    public BookingDTO getBooking(@PathParam("bookingId") UUID boookingId) {
        return this.bookingRepository.fetchBooking(boookingId);
    }

    @POST
    public Response createBooking(BookingDTO bookingDTO) {
        BookingDTO bookingManaged = this.bookingRepository.insertBooking(bookingDTO);
        return Response
            .created(URI.create(Routes.BOOKING + "/" + bookingManaged.id()))
            .entity(bookingManaged)
            .build();
    }

    @PUT
    @Path("/{bookingId}")
    public Response updateBooking(@PathParam("bookingId") UUID bookingId, BookingDTO bookingDTO) {
        Response response;
        if (nonNull(bookingId) && nonNull(this.bookingRepository.fetchBooking(bookingId))) {
            this.bookingRepository.updateBooking(bookingId, bookingDTO);
            response = Response
                .ok(bookingDTO)
                .build();
        } else {
            BookingDTO managedBooking = this.bookingRepository.insertBooking(bookingDTO);
            response = Response
                .created(URI.create(Routes.TEAM + "/" + managedBooking.id()))
                .entity(managedBooking)
                .build();
        }
        return response;
    }

    @DELETE
    @Path("/{bookingId}")
    public Response removeBooking(@PathParam("bookingId") UUID bookingId) {
        this.bookingRepository.deleteBooking(bookingId);
        return Response.noContent().build();
    }
}
