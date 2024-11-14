package su.arlet.soa2.controller;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpaceMarineCreator {
    private String name;
    private CoordinatesPresenter coordinates;
    private java.time.LocalDateTime creationDate;
    private Integer health;
    private Long heartCount;
    private String achievements;
    private String weaponType;
    private String chapterName;
}

