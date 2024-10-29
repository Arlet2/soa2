package su.arlet.soa2.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import su.arlet.soa2.core.Coordinates;
import su.arlet.soa2.core.SpaceMarine;
import su.arlet.soa2.core.Weapon;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class SpaceMarineRepo {
    @Autowired
    private JdbcTemplate template;

    public SpaceMarine getByID(Integer id) {
        var spaceMarines = template.query(
                """
                            SELECT * FROM space_marines
                            WHERE id = $1
                        """,
                (rs, rowNum) -> {
                    var coordinates = new Coordinates(rs.getFloat("x"), rs.getInt("y"));
                    var weapon = Weapon.valueOf(rs.getString("weapon"));

                    return new SpaceMarine(
                            rs.getInt("id"),
                            rs.getString("name"),
                            coordinates,
                            rs.getTimestamp("creation_date").toLocalDateTime(),
                            rs.getInt("health"),
                            rs.getLong("heart_count"),
                            rs.getString("achievements"),
                            weapon,
                            rs.getLong("chapter_id")
                    );
                }, id
        );

        return spaceMarines.getFirst();
    }

    public Long create(SpaceMarine spaceMarine) {
        return template.queryForObject(
                """
                        INSERT INTO space_marines
                        (
                            name, x, y,
                            creation_date, health, heart_count,
                            achievements, weapon, chapter_id
                        )
                        VALUES
                        (
                            $1, $2, $3,
                            $4, $5, $6,
                            $7, $8, $9
                        )
                        
                        RETURNING id
                        """,
                Long.class,
                spaceMarine.getName(), spaceMarine.getCoordinates().getX(), spaceMarine.getCoordinates().getY(),
                Timestamp.from(Instant.now()), spaceMarine.getHealth(), spaceMarine.getHeartCount(),
                spaceMarine.getAchievements(), spaceMarine.getWeaponType(), spaceMarine.getChapterID()
        );
    }
}
