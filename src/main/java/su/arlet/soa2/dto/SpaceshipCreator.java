package su.arlet.soa2.dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "spaceship"
)
public class SpaceshipCreator {
    private String name;
}
