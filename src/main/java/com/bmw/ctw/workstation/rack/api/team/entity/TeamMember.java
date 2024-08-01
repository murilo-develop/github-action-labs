package com.bmw.ctw.workstation.rack.api.team.entity;

import com.bmw.ctw.workstation.rack.api.infrastructure.database.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.util.Objects;

import static com.bmw.ctw.workstation.rack.api.team.entity.TeamMember.FIND_ALL;
import static com.bmw.ctw.workstation.rack.api.team.entity.TeamMember.TEAM_MEMBER_TABLE_NAME;

@Entity
@Table(name = TEAM_MEMBER_TABLE_NAME)
@NamedQuery(
    name = FIND_ALL,
    query = "Select tm From TeamMember tm"
)
public class TeamMember extends BaseEntity {
    public static final String TEAM_MEMBER_TABLE_NAME = "T_TEAM_MEMBER";
    public static final String FIND_ALL = "TeamMember.FIND_ALL";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", nullable = false)
    public Team team;

    @Column(name = "NAME", nullable = false)
    public String name;

    @Column(name = "CTW_CODE", nullable = false)
    public String ctwCode;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    public Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeamMember that = (TeamMember) o;
        return Objects.equals(this.ctwCode, that.ctwCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.ctwCode);
    }
}
