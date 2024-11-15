package su.arlet.soa2.dto.spaceMarine;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import su.arlet.soa2.core.Chapter;
import su.arlet.soa2.dto.chapter.ChapterPresenter;
import su.arlet.soa2.dto.coordinates.CoordinatesPresenter;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "spaceMarine"
)
public class SpaceMarinePresenter {
    private Long id;
    private String name;
    private CoordinatesPresenter coordinates;
    private String creationDate;
    private Integer health;
    private Long heartCount;
    private String achievements;
    private String weaponType;
    private ChapterPresenter chapter;
}


