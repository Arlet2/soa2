package su.arlet.soa2.dto.spaceMarine;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import su.arlet.soa2.dto.coordinates.CoordinatesPresenter;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "spaceMarine"
)
public class SpaceMarineCreator {
    @NotNull
    private String name;
    @NotNull
    private CoordinatesPresenter coordinates;
    @NotNull
    private Integer health;
    @NotNull
    private Long heartCount;
    @NotNull
    private String achievements;
    @NotNull
    private String weaponType;
    @NotNull
    private String chapterName;
}

