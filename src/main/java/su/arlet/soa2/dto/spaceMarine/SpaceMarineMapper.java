package su.arlet.soa2.dto.spaceMarine;


import su.arlet.soa2.core.SpaceMarine;
import org.mapstruct.*;

@Mapper
public interface SpaceMarineMapper {

    @Mapping(source = "coordinates.x", target = "x")
    @Mapping(source = "coordinates.y", target = "y")
    SpaceMarine toSpaceMarine(SpaceMarinePresenter spaceMarinePresenter);

    SpaceMarinePresenter toSpaceMarinePresenter(SpaceMarine spaceMarine);



}
