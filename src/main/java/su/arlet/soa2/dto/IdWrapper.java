package su.arlet.soa2.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IdWrapper {
    @JacksonXmlProperty(localName = "id")
    private Long id;

    public IdWrapper(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}