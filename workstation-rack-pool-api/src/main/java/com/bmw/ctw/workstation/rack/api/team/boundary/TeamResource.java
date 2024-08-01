package com.bmw.ctw.workstation.rack.api.team.boundary;

import com.bmw.ctw.workstation.rack.api.infrastructure.constants.boundary.Routes;
import com.bmw.ctw.workstation.rack.api.team.entity.Team;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path(Routes.TEAM)
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {
    @POST
    @Transactional
    public Response createTeam(Team team) {
        team.persist();
        return Response
            .created(URI.create(Routes.TEAM + "/" + team.id))
            .entity(team)
            .build();
    }
}
