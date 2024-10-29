package su.arlet.soa2.core;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpaceMarine {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotBlank
    @NotNull
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    @NotNull
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @NotNull
    @Positive
    private Integer health; //Поле не может быть null, Значение поля должно быть больше 0

    @Positive
    @Max(3)
    @NotNull
    private Long heartCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3

    @NotNull
    private String achievements; //Поле может быть null

    private Weapon weaponType; //Поле может быть null

    @NotNull
    private Long chapterID; //Поле не может быть null
}