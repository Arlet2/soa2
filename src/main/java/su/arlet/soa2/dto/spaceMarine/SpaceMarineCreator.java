package su.arlet.soa2.dto.spaceMarine;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import su.arlet.soa2.dto.coordinates.CoordinatesPresenter;

import java.util.Optional;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "spaceMarine"
)
public class SpaceMarineCreator {
    @NotNull
    private String name;
    @NotNull
    @Valid
    private CoordinatesPresenter coordinates;

    private Integer health;
    @NotNull
    @Min(1)
    @Max(3)
    private Long heartCount;

    private String achievements;

    private String weaponType;

    private Optional<String> chapterName;
}

