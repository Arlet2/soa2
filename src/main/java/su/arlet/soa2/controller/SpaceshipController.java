package su.arlet.soa2.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.arlet.soa2.dto.IdWrapper;
import su.arlet.soa2.dto.SpaceshipCreator;
import su.arlet.soa2.service.SpaceshipService;

@AllArgsConstructor
@RestController
@RequestMapping("/spaceships")
public class SpaceshipController {

    private SpaceshipService spaceshipService;

    @PostMapping(
            consumes = "application/xml",
            produces = "application/xml"
    )
    public IdWrapper createSpaceship(@RequestBody @Validated SpaceshipCreator spaceship) {
        return new IdWrapper(spaceshipService.createSpaceship(spaceship));
    }

    @PostMapping(
            path = "/{id}/undeploy-all"
    )
    public void undeployAllSpaceMarines(@RequestBody @Validated Long id) {
        spaceshipService.undeployAllSpaceMarines(id);
    }
}
