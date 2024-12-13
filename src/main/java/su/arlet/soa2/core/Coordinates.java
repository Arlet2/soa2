package su.arlet.soa2.core;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JacksonXmlRootElement(
        localName = "coordinates"
)
@AllArgsConstructor
public class Coordinates {

    @NotNull
    private BigDecimal x; //Поле не может быть null

    @Max(883)
    private BigDecimal y; //Максимальное значение поля: 883
}
