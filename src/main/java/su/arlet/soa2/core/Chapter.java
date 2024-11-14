package su.arlet.soa2.core;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chapter {

    private Long id;

    @NotNull
    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Positive
    @Max(1000)
    private long marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
}
