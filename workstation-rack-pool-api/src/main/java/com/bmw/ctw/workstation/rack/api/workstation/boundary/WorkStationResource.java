package com.bmw.ctw.workstation.rack.api.workstation.boundary;

import com.bmw.ctw.workstation.rack.api.infrastructure.constants.boundary.Routes;
import com.bmw.ctw.workstation.rack.api.workstation.entity.WorkStation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path(Routes.WORK_STATION)
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WorkStationResource {
    @POST
    @Transactional
    public Response createWorkStation(WorkStation workStation) {
        workStation.persist();
        return Response
            .created(URI.create(Routes.WORK_STATION + "/" + workStation.id))
            .entity(workStation)
            .build();
    }
}
