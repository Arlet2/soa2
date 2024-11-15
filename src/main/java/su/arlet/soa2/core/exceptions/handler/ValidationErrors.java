package su.arlet.soa2.core.exceptions.handler;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "validationErrors")
public class ValidationErrors {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "error")
    private List<Error> errors;

    public ValidationErrors(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void addError(Error error) {
        this.errors.add(error);
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}