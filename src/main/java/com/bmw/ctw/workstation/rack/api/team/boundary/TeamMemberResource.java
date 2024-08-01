package com.bmw.ctw.workstation.rack.api.team.boundary;

import com.bmw.ctw.workstation.rack.api.infrastructure.constants.boundary.Routes;
import com.bmw.ctw.workstation.rack.api.team.control.TeamMemberRepository;
import com.bmw.ctw.workstation.rack.api.team.entity.TeamMember;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path(Routes.TEAM_MEMBER)
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamMemberResource {
    private final TeamMemberRepository teamMemberRepository;

    @Inject
    public TeamMemberResource(final TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @POST
    @Transactional
    public Response createMember(TeamMember teamMember) {
        return Response
            .created(URI.create(Routes.TEAM_MEMBER + "/" + this.teamMemberRepository.insertTeamMember(teamMember).id))
            .entity(teamMember)
            .build();
    }
}
