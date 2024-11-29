package su.arlet.soa2.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.arlet.soa2.dto.SpaceshipCreator;
import su.arlet.soa2.repo.SpaceshipRepo;

@AllArgsConstructor
@Service
public class SpaceshipService {

    private final SpaceshipRepo spaceshipRepository;

    public Long createSpaceship(SpaceshipCreator spaceship) {
        return spaceshipRepository.createSpaceship(spaceship.getName());
    }

    public void undeployAllSpaceMarines(Long id) {
        spaceshipRepository.undeployAllSpaceMarines(id);
    }

}
