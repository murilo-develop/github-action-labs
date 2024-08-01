package com.bmw.ctw.workstation.rack.api.team.entity;

import com.bmw.ctw.workstation.rack.api.infrastructure.database.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.util.Objects;

import static com.bmw.ctw.workstation.rack.api.team.entity.Team.TEAM_TABLE_NAME;
import static java.util.Objects.hash;

@Entity
@Table(name = TEAM_TABLE_NAME)
@NamedQuery(
    name = Team.FIND_ALL,
    query = "Select t From Team t"
)
public class Team extends BaseEntity {
    public static final String TEAM_TABLE_NAME = "T_TEAM";
    public static final String FIND_ALL = "Team.FIND_ALL";

    @Column(name = "NAME", nullable = false)
    public String name;

    @Column(name = "PRODUCT", nullable = false)
    public String product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return Objects.equals(this.name, team.name) && Objects.equals(this.product, team.product);
    }

    @Override
    public int hashCode() {
        return hash(this.name, this.product);
    }
}
