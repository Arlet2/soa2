package su.arlet.soa2.dto.coordinates;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "coordinates"
)
public class CoordinatesPresenter {
    @NotNull
    @DecimalMin("-674.0")
    private BigDecimal x;

    @NotNull
    @DecimalMax("883.0")
    private BigDecimal y;
}
