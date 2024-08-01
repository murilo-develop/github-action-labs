package com.bmw.ctw.workstation.rack.api.workstation.entity;

import com.bmw.ctw.workstation.rack.api.infrastructure.database.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

import static com.bmw.ctw.workstation.rack.api.workstation.entity.WorkStation.WORK_STATION_TABLE_NAME;
import static java.util.Objects.hash;

@Entity
@Table(name = WORK_STATION_TABLE_NAME)
public class WorkStation extends BaseEntity {
    public static final String WORK_STATION_TABLE_NAME = "T_WORK_STATION_RACK";

    @Column(name = "NAME", nullable = false)
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkStation workStation = (WorkStation) o;
        return Objects.equals(this.id, workStation.id);
    }

    @Override
    public int hashCode() {
        return hash(this.id);
    }
}
