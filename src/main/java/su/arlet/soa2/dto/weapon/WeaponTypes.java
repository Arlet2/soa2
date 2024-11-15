package su.arlet.soa2.dto.weapon;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import su.arlet.soa2.core.Weapon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@JacksonXmlRootElement(localName = "weaponTypes")
@Data
public class WeaponTypes {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "weapon")
    private List<Weapon> weaponList = Arrays.stream(Weapon.values()).collect(Collectors.toList());



}
