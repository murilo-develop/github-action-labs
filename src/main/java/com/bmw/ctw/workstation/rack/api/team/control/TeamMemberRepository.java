package com.bmw.ctw.workstation.rack.api.team.control;

import com.bmw.ctw.workstation.rack.api.team.entity.Team;
import com.bmw.ctw.workstation.rack.api.team.entity.TeamMember;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;

import static java.util.Objects.nonNull;

@Dependent
public class TeamMemberRepository implements PanacheRepository<TeamMember> {
    @Transactional
    public TeamMember insertTeamMember(TeamMember teamMember) {
        teamMember.team = resolveTeam(teamMember);
        teamMember.persist();
        return teamMember;
    }

    private Team resolveTeam(TeamMember teamMember) {
        Team team = teamMember.team;
        if (nonNull(team)) {
            if (nonNull(team.id)) {
                team = Team.findById(team.id);
            } else {
                team.persist();
            }
        }
        return team;
    }
}
