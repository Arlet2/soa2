package su.arlet.service;

import su.arlet.core.StarshipCreator;
import su.arlet.gateway.MarineGateway;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Singleton
public class StarshipService {

    @Inject
    private MarineGateway marineGateway;

    public Response uploadSpaceMarine(long id, long starshipId) {
        var response = marineGateway.unloadSpaceMarine(id, starshipId);

        switch(response.getStatus()) {
            case 200:
                return Response.ok().build();
            case 404:
                return Response.status(404).build();
            case 500:
               return Response.serverError().build();
            default:
                throw new EJBTransactionRolledbackException("Unknown error");
        }
    }

    public Response uploadAll() {
        return Response.ok().entity(marineGateway.unloadSpaceMarine(1,1)).build();
    }

    public Response undeployAll(long starshipId) {
        var response = marineGateway.undeployAll(starshipId);
        switch(response.getStatus()) {
            case 200:
                return Response.ok().build();
            case 404:
                return Response.status(404).build();
            case 500:
                return Response.serverError().build();
            default:
                throw new EJBTransactionRolledbackException("Unknown error");
        }
    }

    public Response createStarship(StarshipCreator starship) {
        var response = marineGateway.createStarship(starship);
        switch(response.getStatus()) {
            case 201:
                return Response.status(201).build();
            case 404:
                return Response.status(404).build();
            case 500:
                return Response.serverError().build();
            default:
                throw new EJBTransactionRolledbackException("Unknown error");
        }
    }
}
