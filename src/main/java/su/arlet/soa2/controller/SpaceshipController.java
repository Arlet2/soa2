package su.arlet.soa2.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import su.arlet.soa2.dto.IdWrapper;
import su.arlet.soa2.dto.SpaceshipCreator;
import su.arlet.soa2.service.SpaceshipService;

@AllArgsConstructor
@RestController
@RequestMapping("/starships")
public class SpaceshipController {

    private SpaceshipService spaceshipService;

    @PostMapping(
            consumes = "application/xml",
            produces = "application/xml",
            path = "/create"
    )
    public IdWrapper createSpaceship(@RequestBody @Validated SpaceshipCreator spaceship) {
        return new IdWrapper(spaceshipService.createSpaceship(spaceship));
    }

    @PostMapping(
            path = "/{id}/undeploy-all"
    )
    public void undeployAllSpaceMarines(@PathVariable @Validated Long id) {
        spaceshipService.undeployAllSpaceMarines(id);
    }
}
