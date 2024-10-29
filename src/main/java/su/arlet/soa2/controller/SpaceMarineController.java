package su.arlet.soa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import su.arlet.soa2.service.ChapterService;
import su.arlet.soa2.service.SpaceMarineService;

@RestController
@RequestMapping("/space-marines")
public class SpaceMarineController {
    @Autowired
    private SpaceMarineService spaceMarineService;

    @Autowired
    private ChapterService chapterService;

    @GetMapping(
            produces = "application/xml"
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
}
