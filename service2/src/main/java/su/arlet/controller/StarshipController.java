package su.arlet.controller;

import su.arlet.core.StarshipCreator;
import su.arlet.service.StarshipService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/starships")
public class StarshipController {

    @Inject
    private StarshipService starshipService;

    @POST
    @Path("/{starship-id}/unload/{space-marine-id}")
    public Response unloadSpaceMarine(@PathParam("starship-id") long starshipId, @PathParam("space-marine-id") long spaceMarineId) {
        return starshipService.uploadSpaceMarine(spaceMarineId,starshipId);
    }

    @POST
    @Path("/{starship-id}/unload-all")
    public Response unloadAll(@PathParam("starship-id") long starshipId) {
        return starshipService.undeployAll(starshipId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response createStarship(StarshipCreator starship) {
        return starshipService.createStarship(starship);
    }
}
