package su.arlet.controller;

import su.arlet.dto.StarshipCreator;
import su.arlet.ejb.StarshipServiceRemote;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/starships")
public class StarshipController {

    @EJB(lookup = "ejb:/ejb-module-0.0.1-SNAPSHOT/StarshipServiceBean!su.arlet.ejb.StarshipServiceRemote")
    private StarshipServiceRemote starshipService;

    @POST
    @Path("/{starship-id}/unload/{space-marine-id}")
    public Response unloadSpaceMarine(@PathParam("starship-id") long starshipId, @PathParam("space-marine-id") long spaceMarineId) {
        return Response.status(starshipService.unloadSpaceMarine(starshipId, spaceMarineId)).build() ;
    }

    @POST
    @Path("/{starship-id}/unload-all")
    public Response unloadAll(@PathParam("starship-id") long starshipId) {
            return Response.status(starshipService.undeployAll(starshipId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response createStarship(StarshipCreator starship) {
        return Response.status(starshipService.createStarship(starship)).build();
    }

    @GET
    @Path("/health")
    public Response health() {
        System.out.println("УРАААА ГОРНЫЙ ОТКРЫЛИ");
        return Response.ok().build();
    }
}