package su.arlet.soa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.arlet.soa2.core.SpaceMarine;
import su.arlet.soa2.repo.SpaceMarineRepo;

@Service
public class SpaceMarineService {
    @Autowired
    private SpaceMarineRepo spaceMarineRepo;

    public SpaceMarine getSpaceMarine(Integer id) {
        return spaceMarineRepo.getByID(id);
    }
}
