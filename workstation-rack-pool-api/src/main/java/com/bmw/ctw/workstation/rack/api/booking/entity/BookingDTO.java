package com.bmw.ctw.workstation.rack.api.booking.entity;

import com.bmw.ctw.workstation.rack.api.team.entity.TeamMemberDTO;
import com.bmw.ctw.workstation.rack.api.workstation.entity.WorkStation;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public record BookingDTO(UUID getId,
                         TeamMemberDTO getTeamMember,
                         WorkStation getWorkStation,
                         LocalDateTime getBookingFrom,
                         LocalDateTime getBookingTo,
                         BookingStatus getStatus) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookingDTO that = (BookingDTO) o;
        return Objects.equals(this.getId(), that.getId())
            && Objects.equals(this.getTeamMember(), that.getTeamMember())
            && Objects.equals(this.getWorkStation(), that.getWorkStation())
            && Objects.equals(this.getBookingFrom(), that.getBookingFrom())
            && Objects.equals(this.getBookingTo(), that.getBookingTo())
            && this.getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getTeamMember(), this.getWorkStation(), this.getBookingFrom(), this.getBookingTo(), this.getStatus());
    }

    public static class Builder {
        public UUID getId;
        public TeamMemberDTO getTeamMember;
        public WorkStation getWorkStation;
        public LocalDateTime getBookingFrom;
        public LocalDateTime getBookingTo;
        public BookingStatus getStatus;

        public Builder with(Consumer<Builder> builder) {
            builder.accept(this);
            return this;
        }

        public BookingDTO build() {
            return new BookingDTO(
                this.getId,
                this.getTeamMember,
                this.getWorkStation,
                this.getBookingFrom,
                this.getBookingTo,
                this.getStatus
            );
        }
    }
}
