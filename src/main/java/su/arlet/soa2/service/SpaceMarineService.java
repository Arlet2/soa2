package su.arlet.soa2.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.SortField;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import su.arlet.soa2.controller.SpaceMarineCreator;
import su.arlet.soa2.controller.SpaceMarinePresenter;
import su.arlet.soa2.core.Chapter;
import su.arlet.soa2.core.Coordinates;
import su.arlet.soa2.core.SpaceMarine;
import su.arlet.soa2.core.Weapon;
import su.arlet.soa2.repo.SpaceMarineRepo;

import java.util.Arrays;

import static org.jooq.impl.DSL.*;



@AllArgsConstructor
@Service
public class SpaceMarineService {

    private SpaceMarineRepo spaceMarineRepo;
    private ChapterService chapterService;

    public SpaceMarine getSpaceMarine(Integer id) {
        return spaceMarineRepo.getByID(id);
    }

    public Page<SpaceMarine> getSpaceMarines(int page, int size, String[] sortBy, String[] direction) {
        var c = Arrays.stream(sortBy).map(String::toLowerCase)
                .map(String::trim).map(a -> a.charAt(0)=='-'?
                        field(a.substring(1)).desc() :
                        a.charAt(0)=='+' ? field(a.substring(1)).asc()
                                : field(a).asc()).toList();
        var condition = Arrays.stream(direction).map(String::toLowerCase)
                .map(DSL::condition)
                .reduce(noCondition(), Condition::and);
        return spaceMarineRepo.findAll(page, size, c, condition);
    }

    public Long createSpaceMarine(SpaceMarineCreator spaceMarineCreateRequest) {
        var chapterId = chapterService.findChapterByName(spaceMarineCreateRequest.getChapterName()).getId();
        var coordinates = new Coordinates(spaceMarineCreateRequest.getCoordinates().getX(), spaceMarineCreateRequest.getCoordinates().getY());
        var spaceMarine = new SpaceMarine(null,
                spaceMarineCreateRequest.getName(),
                coordinates,
                spaceMarineCreateRequest.getCreationDate(),
                spaceMarineCreateRequest.getHealth(),
                spaceMarineCreateRequest.getHeartCount(),
                spaceMarineCreateRequest.getAchievements(),
                Weapon.valueOf(spaceMarineCreateRequest.getWeaponType()),
                chapterId
        );
        return spaceMarineRepo.create(spaceMarine);
    }
}
