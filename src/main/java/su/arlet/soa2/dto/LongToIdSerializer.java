package su.arlet.soa2.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class LongToIdSerializer extends StdSerializer<Long> {

    public LongToIdSerializer() {
        this(null);
    }

    public LongToIdSerializer(Class<Long> t) {
        super(t);
    }

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("id");
        gen.writeNumber(value);
        gen.writeEndObject();
    }
}