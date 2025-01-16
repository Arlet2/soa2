package su.arlet.controller;

import su.arlet.dto.StarshipCreator;
import su.arlet.ejb.StarshipServiceRemote;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@WebService(targetNamespace = "http://example.com/webservices")
public class StarshipController {

    @EJB(lookup = "ejb:/ejb-module-0.0.1-SNAPSHOT/StarshipServiceBean!su.arlet.ejb.StarshipServiceRemote")
    private StarshipServiceRemote starshipService;

    @WebMethod
    public Response unloadSpaceMarine(
            @WebParam(name = "starship-id") long starshipId,
            @WebParam(name = "space-marine-id") long spaceMarineId
    ) {
        return Response.status(starshipService.unloadSpaceMarine(starshipId, spaceMarineId)).build();
    }

    @WebMethod
    public Response unloadAll(@WebParam(name="starship-id") long starshipId) {
        return Response.status(starshipService.undeployAll(starshipId)).build();
    }

    @WebMethod
    public Response createStarship(@WebParam(name="starship") StarshipCreator starship) {
        return Response.status(starshipService.createStarship(starship)).build();
    }
}