package su.arlet.soa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import su.arlet.soa2.core.Chapter;
import su.arlet.soa2.core.SpaceMarine;
import su.arlet.soa2.service.ChapterService;
import su.arlet.soa2.service.SpaceMarineService;
import java.util.List;

@RestController
@RequestMapping("/space-marines")
public class SpaceMarineController {
    @Autowired
    private SpaceMarineService spaceMarineService;

    @Autowired
    private ChapterService chapterService;

    @GetMapping(
               path = "/{id}",
              produces = "application/json"
    )
    public SpaceMarinePresenter getSpaceMarine(@RequestParam(name="id") int id) {
        var spaceMarine = spaceMarineService.getSpaceMarine(id);
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
                chapterPresenter
        );
    }

    @PostMapping
    public Long createSpaceMarine(@RequestBody SpaceMarineCreator spaceMarine) {
        return spaceMarineService.createSpaceMarine(spaceMarine);
    }

    @GetMapping(
            produces = "application/xml"
            )
    public Page<SpaceMarine> getSpaceMarines (@RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "10") int size, @RequestParam(name="sort", defaultValue = "id") String[] sort, @RequestParam(name="direction", defaultValue = "") String[] direction) {
        return spaceMarineService.getSpaceMarines(page, size, sort, direction);

    }
}
