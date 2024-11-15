package su.arlet.soa2.dto.coordinates;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "coordinates"
)
public class CoordinatesPresenter {
    @NotNull
    private Float x;
    @NotNull
    private Double y;
}
