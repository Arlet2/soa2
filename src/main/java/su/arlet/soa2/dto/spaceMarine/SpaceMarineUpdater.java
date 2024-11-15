package su.arlet.soa2.dto.spaceMarine;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import su.arlet.soa2.dto.chapter.ChapterPresenter;
import su.arlet.soa2.dto.coordinates.CoordinatesPresenter;

import java.util.Optional;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "spaceMarine"
)
public class SpaceMarineUpdater {
    private Optional<String> name;
    private Optional<CoordinatesPresenter> coordinates;
    private Optional<java.time.LocalDateTime> creationDate;
    private Optional<Integer> health;
    private Optional<Long> heartCount;
    private Optional<String> achievements;
    private Optional<String> weaponType;
    private Optional<String> chapterName;
}
