package su.arlet.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.Pool;
import su.arlet.dto.StarshipCreator;
import su.arlet.gateway.MarineGateway;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
@Pool("slsb-strict-max-pool")
public class StarshipServiceBean implements StarshipServiceRemote {

    @EJB
    private MarineGateway marineGateway;

    @Override
    public int createStarship(StarshipCreator starship) {
        var response = marineGateway.createStarship(starship);
        if (response.getStatus()==500) {
            return 503;
        }
        return response.getStatus();

    }

    @Override
    public int unloadSpaceMarine(long starshipId, long spaceMarineId) {
        var response = marineGateway.unloadSpaceMarine(spaceMarineId, starshipId);
        if (response.getStatus()==500) {
            return 503;
        }
        return response.getStatus();
    }

    @Override
    public int undeployAll(long starshipId) {
        var response = marineGateway.undeployAll(starshipId);
        if (response.getStatus()==500) {
            return 503;
        }
        return response.getStatus();
    }
}