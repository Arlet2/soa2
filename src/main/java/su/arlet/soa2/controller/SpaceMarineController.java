package su.arlet.soa2.controller;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import su.arlet.soa2.core.SpaceMarine;
import su.arlet.soa2.dto.Filters;
import su.arlet.soa2.dto.IdWrapper;
import su.arlet.soa2.dto.chapter.ChapterPresenter;
import su.arlet.soa2.dto.coordinates.CoordinatesPresenter;
import su.arlet.soa2.dto.spaceMarine.SpaceMarineCreator;
import su.arlet.soa2.dto.spaceMarine.SpaceMarineList;
import su.arlet.soa2.dto.spaceMarine.SpaceMarinePresenter;
import su.arlet.soa2.dto.spaceMarine.SpaceMarineUpdater;
import su.arlet.soa2.dto.weapon.WeaponTypes;
import su.arlet.soa2.service.ChapterService;
import su.arlet.soa2.service.SpaceMarineService;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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
    public SpaceMarinePresenter getSpaceMarine(@PathVariable(name="id") Long id) {
        var spaceMarine = spaceMarineService.getSpaceMarine(id);
        var coordinatesPresenter = new CoordinatesPresenter(
                spaceMarine.getCoordinates().getX(),
                spaceMarine.getCoordinates().getY()
        );
        var chapterPresenter =  spaceMarine.getChapter().getName() == null ? null : new ChapterPresenter(
                spaceMarine.getChapter().getName(),
                spaceMarine.getChapter().getMarinesCount()
        );


        return new SpaceMarinePresenter(
                id,
                spaceMarine.getName(),
                coordinatesPresenter,
                DateTimeFormatter.ISO_INSTANT.format(spaceMarine.getCreationDate().atZone(ZoneOffset.UTC).toInstant()),
                spaceMarine.getHealth(),
                spaceMarine.getHeartCount(),
                spaceMarine.getAchievements(),
                spaceMarine.getWeaponType().name(),
                spaceMarine.getStarshipId(),
                chapterPresenter
        );
    }

    @PostMapping(
            consumes = "application/xml",
            produces = "application/xml"
    )
    public IdWrapper createSpaceMarine(@RequestBody @Validated SpaceMarineCreator spaceMarine) {
        return new IdWrapper(spaceMarineService.createSpaceMarine(spaceMarine));
    }

    @DeleteMapping(
            path = "/{id}",
            produces = "application/xml"
    )
    public void deleteSpaceMarine(@PathVariable(name="id") Long id) {
      spaceMarineService.deleteSpaceMarine(id);
    }

    @GetMapping(
            produces = "application/xml"
            )
    public SpaceMarineList getSpaceMarines (@RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="limit", defaultValue = "10") int limit, @RequestParam(name="sort", defaultValue = "") String[] sort, @RequestParam(name="filter", defaultValue = "") String[] filter) {

        var spaceMarinePage = spaceMarineService.getSpaceMarines(page, limit, sort, new Filters(filter));

        return (new SpaceMarineList(
                spaceMarinePage.getContent().stream().map(spaceMarine -> {
                    var coordinatesPresenter = new CoordinatesPresenter(
                            spaceMarine.getCoordinates().getX(),
                            spaceMarine.getCoordinates().getY()
                    );
                    var chapterPresenter = spaceMarine.getChapter().getName() == null ? null : new ChapterPresenter(
                            spaceMarine.getChapter().getName(),
                            spaceMarine.getChapter().getMarinesCount()
                    );


                    return new SpaceMarinePresenter(
                            spaceMarine.getId().longValue(),
                            spaceMarine.getName(),
                            coordinatesPresenter,
                            DateTimeFormatter.ISO_INSTANT.format(spaceMarine.getCreationDate().atZone(ZoneOffset.UTC).toInstant()),
                            spaceMarine.getHealth(),
                            spaceMarine.getHeartCount(),
                            spaceMarine.getAchievements(),
                            spaceMarine.getWeaponType().name(),
                            spaceMarine.getStarshipId(),
                            chapterPresenter
                    );
                }).toList(),
                spaceMarinePage.getContent().size(),
                spaceMarinePage.getNumber(),
                spaceMarinePage.getTotalPages()
        ));


    }
    @DeleteMapping(
            path = "/chapters/{chapterName}",
            produces = "application/xml"
    )
    public void deleteSpaceMarinesByChapter(@PathVariable(name="chapterName") String chapterName) {
        spaceMarineService.deleteSpaceMarineByChapterName(chapterName);
    }

    @GetMapping(
            path = "creation-date/min",
            produces = "application/xml"
    )
    public SpaceMarinePresenter getSpaceMarineWithMinCreationDate() {
        var spaceMarine = spaceMarineService.getFirstCreatedSpaceMarine();
        var coordinatesPresenter = new CoordinatesPresenter(
                spaceMarine.getCoordinates().getX(),
                spaceMarine.getCoordinates().getY()
        );
        var chapterPresenter = spaceMarine.getChapter().getName() == null ? null : new ChapterPresenter(
                spaceMarine.getChapter().getName(),
                spaceMarine.getChapter().getMarinesCount()
        );


        return new SpaceMarinePresenter(
                spaceMarine.getId().longValue(),
                spaceMarine.getName(),
                coordinatesPresenter,
                spaceMarine.getCreationDate().toString(),
                spaceMarine.getHealth(),
                spaceMarine.getHeartCount(),
                spaceMarine.getAchievements(),
                spaceMarine.getWeaponType().name(),
                spaceMarine.getStarshipId(),

                chapterPresenter
        );
    }

    @GetMapping(
            path = "/weapon-types/unique",
            produces = "application/xml"
    )
    public WeaponTypes getUniqueWeaponTypes() {
        return new WeaponTypes(spaceMarineService.getUniqueWeaponTypes());
    }

    @PostMapping(
            path = "/{id}/starships/{starshipId}/deploy",
            produces = "application/xml"
    )
    public void deploySpaceMarine(@PathVariable(name="id") Long id, @PathVariable(name="starshipId") Long starshipId) {
        spaceMarineService.deploySpaceMarine(id,starshipId);
    }

    @PostMapping(
            path = "/{id}/starships/undeploy",
            produces = "application/xml"
    )
    public void undeploySpaceMarine(@PathVariable(name="id") Long id) {
        spaceMarineService.undeploySpaceMarine(id);
    }


    @PatchMapping(
            path = "/{id}",
            consumes = "application/xml",
            produces = "application/xml"
    )
    public SpaceMarinePresenter updateSpaceMarine(@PathVariable(name="id") Long id, @RequestBody SpaceMarineUpdater spaceMarineUpdater) {
        var spaceMarine =  spaceMarineService.updateSpaceMarineInPlace(id, spaceMarineUpdater);
        var coordinatesPresenter = new CoordinatesPresenter(
                spaceMarine.getCoordinates().getX(),
                spaceMarine.getCoordinates().getY()
        );
        var chapterPresenter = spaceMarine.getChapter().getName() == null ? null : new ChapterPresenter(
                spaceMarine.getChapter().getName(),
                spaceMarine.getChapter().getMarinesCount()
        );



        return new SpaceMarinePresenter(
                id,
                spaceMarine.getName(),
                coordinatesPresenter,
                spaceMarine.getCreationDate().toString(),
                spaceMarine.getHealth(),
                spaceMarine.getHeartCount(),
                spaceMarine.getAchievements(),
                spaceMarine.getWeaponType().name(),
                spaceMarine.getStarshipId(),
                chapterPresenter
        );
    }
}
