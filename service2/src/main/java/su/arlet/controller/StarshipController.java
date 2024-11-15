package su.arlet.controller;

import su.arlet.service.StarshipService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/starships")
public class StarshipController {

    @Inject
    private StarshipService starshipService;

    @POST
    @Path("/{starship-id}/unload/{space-marine-id}")
    public Response unloadSpaceMarine() {
        return Response.ok().entity("Service online unload").build();
    }

    @POST
    @Path("/{starship-id}/unload-all")
    public Response unloadAll() {
        return Response.ok().entity(starshipService.testGetting()).build();
    }

    @POST
    @Path("/")
    public Response createStarship() {
        return Response.ok().entity("Service online").build();
    }
}
