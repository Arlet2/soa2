package su.arlet.ejb;

import su.arlet.dto.StarshipCreator;

import javax.ejb.Remote;
import javax.ws.rs.core.Response;

@Remote
public interface StarshipServiceRemote {
    int createStarship(StarshipCreator starship);
    int unloadSpaceMarine(long starshipId, long spaceMarineId);
    int undeployAll(long starshipId);
}