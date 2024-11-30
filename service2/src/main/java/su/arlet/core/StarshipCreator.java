package su.arlet.core;


import jakarta.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="starship")
public class StarshipCreator {
    @NotNull
    @NotEmpty
    @XmlElement(required = true)
    private String name;

    public StarshipCreator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
