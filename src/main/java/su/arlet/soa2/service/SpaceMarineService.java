package su.arlet.soa2.service;

import lombok.AllArgsConstructor;
import org.jooq.Condition;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import su.arlet.soa2.dto.spaceMarine.SpaceMarineCreator;
import su.arlet.soa2.core.Coordinates;
import su.arlet.soa2.core.SpaceMarine;
import su.arlet.soa2.core.Weapon;
import su.arlet.soa2.dto.spaceMarine.SpaceMarineUpdater;
import su.arlet.soa2.repo.SpaceMarineRepo;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.jooq.impl.DSL.*;



@AllArgsConstructor
@Service
public class SpaceMarineService {

    private SpaceMarineRepo spaceMarineRepo;
    private ChapterService chapterService;

    public SpaceMarine getSpaceMarine(Long id) {
        return spaceMarineRepo.getByID(id);
    }

    public void deleteSpaceMarine(Long id) {
        spaceMarineRepo.deleteByID(id);
    }

    public Page<SpaceMarine> getSpaceMarines(int page, int size, String[] sortBy, String[] direction) {
        var c = Arrays.stream(sortBy).map(String::toLowerCase)
                .map(String::trim).map(a -> a.charAt(0)=='-'?
                        field(a.substring(1) ).desc() :
                        a.charAt(0)=='+' ? field(a.substring(1)).asc()
                                : field(a).asc()).toList();
        var condition = Arrays.stream(direction).map(String::toLowerCase)
                .map(DSL::condition)
                .reduce(noCondition(), Condition::and);
        return spaceMarineRepo.findAll(page, size, c, condition);
    }

    public Long createSpaceMarine(SpaceMarineCreator spaceMarineCreateRequest) {
        var chapter = chapterService.getChapterByName(spaceMarineCreateRequest.getChapterName());
        var coordinates = new Coordinates(spaceMarineCreateRequest.getCoordinates().getX(), spaceMarineCreateRequest.getCoordinates().getY());
        var spaceMarine = new SpaceMarine(null,
                spaceMarineCreateRequest.getName(),
                coordinates,
                LocalDateTime.now(),
                spaceMarineCreateRequest.getHealth(),
                spaceMarineCreateRequest.getHeartCount(),
                spaceMarineCreateRequest.getAchievements(),
                Weapon.valueOf(spaceMarineCreateRequest.getWeaponType()),
                chapter
        );
        return spaceMarineRepo.create(spaceMarine);
    }

    public SpaceMarine updateSpaceMarineInPlace(Long i, SpaceMarineUpdater spaceMarineUpdater) {
        var spaceMarine = spaceMarineRepo.getByID(i);

        spaceMarineUpdater.getName().ifPresent(spaceMarine::setName);
        spaceMarineUpdater.getCoordinates().ifPresent(coordinatesPresenter -> {
            spaceMarine.getCoordinates().setX(coordinatesPresenter.getX());
            spaceMarine.getCoordinates().setY(coordinatesPresenter.getY());
        });
        spaceMarineUpdater.getCreationDate().ifPresent(spaceMarine::setCreationDate);
        spaceMarineUpdater.getHealth().ifPresent(spaceMarine::setHealth);
        spaceMarineUpdater.getHeartCount().ifPresent(spaceMarine::setHeartCount);
        spaceMarineUpdater.getAchievements().ifPresent(spaceMarine::setAchievements);
        spaceMarineUpdater.getWeaponType().ifPresent(s -> spaceMarine.setWeaponType(Weapon.valueOf(s)));
        spaceMarineUpdater.getChapterName().ifPresent(chapterName -> {
            var chapter = chapterService.getChapterByName(chapterName);
            spaceMarine.setChapter(chapter);
        });


        return spaceMarine;
    }
}
