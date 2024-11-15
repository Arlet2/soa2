package su.arlet.soa2.core.exceptions.handler;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Error {
    @JacksonXmlProperty(localName = "reason")
    private String reason;

    public Error(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}