package su.arlet.soa2.dto.spaceMarine;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@JacksonXmlRootElement(localName = "result")
@AllArgsConstructor
@Data
public class SpaceMarineList {

    @JacksonXmlElementWrapper(localName = "spaceMarines")
    @JacksonXmlProperty(localName = "spaceMarine")
    private List<SpaceMarinePresenter> spaceMarines;
    @JacksonXmlProperty
    private int count;
    @JacksonXmlProperty
    private int currentPage;
    @JacksonXmlProperty
    private int totalPages;



}
