package su.arlet.soa2.core;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JacksonXmlRootElement(
        localName = "coordinates"
)
@AllArgsConstructor
public class Coordinates {

    @NotNull
    private Float x; //Поле не может быть null

    @Max(883)
    private double y; //Максимальное значение поля: 883
}
