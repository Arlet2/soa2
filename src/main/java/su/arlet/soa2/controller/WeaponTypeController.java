package su.arlet.soa2.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.arlet.soa2.core.Weapon;
import su.arlet.soa2.dto.weapon.WeaponTypes;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@RestController()
@RequestMapping("/weapon-types")
public class WeaponTypeController {

    @GetMapping(
            produces = "application/xml"
    )
    public WeaponTypes displayWeaponTypes() {
        return new WeaponTypes();
    }

}
