package su.arlet.dto;

import jakarta.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="starship")
@XmlAccessorType(XmlAccessType.FIELD)
public class StarshipCreator {
    @NotNull
    @NotEmpty
    @XmlElement(required = true)
    private String name;

    public StarshipCreator(String name) {
        this.name = name;
    }

    public StarshipCreator(){
        this.name="default";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}