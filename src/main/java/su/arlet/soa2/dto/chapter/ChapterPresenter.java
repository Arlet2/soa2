package su.arlet.soa2.dto.chapter;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(
        localName = "chapter"
)
public class ChapterPresenter {
    private String name;
    private Long marinesCount;
}
