package su.arlet.soa2.core.exceptions.handler;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Error {
    @JacksonXmlProperty(localName = "reason")
    private String reason;
}