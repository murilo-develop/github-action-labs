package com.bmw.ctw.workstation.rack.api.booking.entity;

import com.bmw.ctw.workstation.rack.api.team.entity.TeamMemberDTO;
import com.bmw.ctw.workstation.rack.api.workstation.entity.WorkStation;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public record BookingDTO(UUID id,
                         TeamMemberDTO teamMember,
                         WorkStation workStation,
                         LocalDateTime bookingFrom,
                         LocalDateTime bookingTo,
                         BookingStatus status) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookingDTO that = (BookingDTO) o;
        return Objects.equals(this.id(), that.id())
            && Objects.equals(this.teamMember(), that.teamMember())
            && Objects.equals(this.workStation(), that.workStation())
            && Objects.equals(this.bookingFrom(), that.bookingFrom())
            && Objects.equals(this.bookingTo(), that.bookingTo())
            && this.status() == that.status();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id(), this.teamMember(), this.workStation(), this.bookingFrom(), this.bookingTo(), this.status());
    }

    public static class Builder {
        public UUID id;
        public TeamMemberDTO teamMember;
        public WorkStation workStation;
        public LocalDateTime bookingFrom;
        public LocalDateTime bookingTo;
        public BookingStatus status;

        public Builder with(Consumer<Builder> builder) {
            builder.accept(this);
            return this;
        }

        public BookingDTO build() {
            return new BookingDTO(
                this.id,
                this.teamMember,
                this.workStation,
                this.bookingFrom,
                this.bookingTo,
                this.status
            );
        }
    }
}
