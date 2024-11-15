package su.arlet.soa2.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import su.arlet.soa2.core.SpaceMarine;
import su.arlet.soa2.dto.chapter.ChapterPresenter;
import su.arlet.soa2.dto.coordinates.CoordinatesPresenter;
import su.arlet.soa2.dto.spaceMarine.SpaceMarineCreator;
import su.arlet.soa2.dto.spaceMarine.SpaceMarinePresenter;
import su.arlet.soa2.dto.spaceMarine.SpaceMarineUpdater;
import su.arlet.soa2.service.ChapterService;
import su.arlet.soa2.service.SpaceMarineService;

@RestController
@AllArgsConstructor
@RequestMapping("/space-marines")
public class SpaceMarineController {
    private SpaceMarineService spaceMarineService;

    private ChapterService chapterService;

    @GetMapping(
               path = "/{id}",
              produces = "application/xml"
    )
    public SpaceMarinePresenter getSpaceMarine(@PathVariable(name="id") int id) {
        var spaceMarine = spaceMarineService.getSpaceMarine(id);
        var coordinatesPresenter = new CoordinatesPresenter(
                spaceMarine.getCoordinates().getX(),
                spaceMarine.getCoordinates().getY()
        );
        var chapter = chapterService.getChapter(spaceMarine.getChapterID());

        return new SpaceMarinePresenter(
                spaceMarine.getName(),
                coordinatesPresenter,
                spaceMarine.getCreationDate(),
                spaceMarine.getHealth(),
                spaceMarine.getHeartCount(),
                spaceMarine.getAchievements(),
                spaceMarine.getWeaponType().name(),
                chapter.getName()
        );
    }

    @PostMapping(
            consumes = "application/xml",
            produces = "application/xml"
    )
    public Long createSpaceMarine(@RequestBody @Validated SpaceMarineCreator spaceMarine) {
        return spaceMarineService.createSpaceMarine(spaceMarine);
    }

    @GetMapping(
            produces = "application/xml"
            )
    public Page<SpaceMarine> getSpaceMarines (@RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "10") int size, @RequestParam(name="sort", defaultValue = "id") String[] sort, @RequestParam(name="direction", defaultValue = "") String[] direction) {
        return spaceMarineService.getSpaceMarines(page, size, sort, direction);
    }

    @PatchMapping(
            path = "/{id}",
            consumes = "application/xml",
            produces = "application/xml"
    )
    public SpaceMarinePresenter updateSpaceMarine(@PathVariable(name="id") int id, @RequestBody SpaceMarineUpdater spaceMarineUpdater) {
        var spaceMarine =  spaceMarineService.updateSpaceMarineInPlace(id, spaceMarineUpdater);
        var coordinatesPresenter = new CoordinatesPresenter(
                spaceMarine.getCoordinates().getX(),
                spaceMarine.getCoordinates().getY()
        );
        var chapter = chapterService.getChapter(spaceMarine.getChapterID());

        var chapterPresenter = new ChapterPresenter(chapter.getName(), chapter.getMarinesCount());

        return new SpaceMarinePresenter(
                spaceMarine.getName(),
                coordinatesPresenter,
                spaceMarine.getCreationDate(),
                spaceMarine.getHealth(),
                spaceMarine.getHeartCount(),
                spaceMarine.getAchievements(),
                spaceMarine.getWeaponType().name(),
                chapter.getName()
        );
    }
}
