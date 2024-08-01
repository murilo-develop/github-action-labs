package com.bmw.ctw.workstation.rack.api.booking.entity;

import com.bmw.ctw.workstation.rack.api.infrastructure.database.entity.BaseEntity;
import com.bmw.ctw.workstation.rack.api.team.entity.TeamMember;
import com.bmw.ctw.workstation.rack.api.workstation.entity.WorkStation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import static com.bmw.ctw.workstation.rack.api.booking.entity.Booking.BOOKING_TABLE_NAME;
import static com.bmw.ctw.workstation.rack.api.booking.entity.Booking.BOOKING_UPDATE;

@Entity
@Table(name = BOOKING_TABLE_NAME)
@NamedQuery(
    name = BOOKING_UPDATE,
    query = """
            Update
                Booking b
            Set
                b.teamMember = :teamMember,
                b.workStation = :workStation,
                b.bookingFrom = :bookingFrom,
                b.bookingTo = :bookingTo,
                b.status = :status
            Where b.id = :id
        """
)
public class Booking extends BaseEntity {
    public static final String BOOKING_TABLE_NAME = "T_BOOKING";
    public static final String BOOKING_UPDATE = "Booking.UPDATE";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_MEMBER_ID", nullable = false)
    public TeamMember teamMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_STATION_RACK_ID", nullable = false)
    public WorkStation workStation;

    @Column(name = "BOOKING_FROM", nullable = false)
    public LocalDateTime bookingFrom;

    @Column(name = "BOOKING_TO", nullable = false)
    public LocalDateTime bookingTo;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    public BookingStatus status;
}
