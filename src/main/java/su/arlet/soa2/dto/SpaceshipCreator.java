package su.arlet.soa2.dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "starship"
)
public class SpaceshipCreator {
    @JacksonXmlProperty(localName = "name")
    @NotNull
    private String name;
}
