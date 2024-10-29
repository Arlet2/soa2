package su.arlet.soa2.controller;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "spaceMarine"
)
public class SpaceMarinePresenter {
    private String name;
    private CoordinatesPresenter coordinates;
    private java.time.LocalDateTime creationDate;
    private Integer health;
    private Long heartCount;
    private String achievements;
    private String weaponType;
    private ChapterPresenter chapter;
}
