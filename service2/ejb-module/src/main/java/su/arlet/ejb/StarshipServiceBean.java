package su.arlet.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.Pool;
import su.arlet.dto.StarshipCreator;
import su.arlet.gateway.MarineGateway;

import javax.xml.ws.WebServiceRef;

@Stateless
@Pool("slsb-strict-max-pool")
public class StarshipServiceBean implements StarshipServiceRemote {

    @EJB
    @WebServiceRef(wsdlLocation =
            "http://localhost:8080/helloservice-war/HelloService?WSDL")
    private static MarineGateway marineGateway;

    @Override
    public int createStarship(StarshipCreator starship) {
        return 200;

    }

    @Override
    public int unloadSpaceMarine(long starshipId, long spaceMarineId) {
        marineGateway.deploy(spaceMarineId, starshipId);

        return 200;
    }

    @Override
    public int undeployAll(long starshipId) {
        marineGateway.undeployAll(starshipId);

        return 200;
    }
}