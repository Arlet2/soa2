package su.arlet.soa2.repo;

import lombok.AllArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.jooq.conf.ParamType;
import org.jooq.impl.QOM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import su.arlet.soa2.core.Coordinates;
import su.arlet.soa2.core.SpaceMarine;
import su.arlet.soa2.core.Weapon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.jooq.impl.DSL.*;

@AllArgsConstructor
@Service
public class SpaceMarineRepo {
    private JdbcTemplate template;
    private DSLContext dsl;



    public SpaceMarine getByID(Integer id) {
        var spaceMarines = template.query(
                """
                            SELECT * FROM space_marines
                            WHERE id = $1
                        """,
                new SpaceMarineRowMapper(), id
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
                            achievements, weapon_type, chapter_id
                        )
                        VALUES
                        (
                            ?, ?, ?,
                            ?, ?, ?,
                            ?, CAST(? AS weapon), ?
                        )
                        
                        RETURNING id
                        """,
                Long.class,

                spaceMarine.getName(), spaceMarine.getCoordinates().getX(), spaceMarine.getCoordinates().getY(),
                Timestamp.from(Instant.now()), spaceMarine.getHealth(), spaceMarine.getHeartCount(),
                spaceMarine.getAchievements(), spaceMarine.getWeaponType().toString(), spaceMarine.getChapterID()
        );
    }

    public Page<SpaceMarine> findAll(int page, int size, Collection<SortField<Object>> sortBy, Condition condition) {
        PageRequest pageRequest = PageRequest.of(page, size);

        String query = dsl.selectFrom(table("space_marines")).where(condition).orderBy(sortBy).limit(size).offset(page*size).getSQL(ParamType.INLINED);
        System.out.println(query);
        List<SpaceMarine> spaceMarines = template.query(query, new SpaceMarineRowMapper());

        long total = template.queryForObject("SELECT count(*) FROM space_marines", Long.class);

        return new PageImpl<>(spaceMarines, pageRequest, total);
    }

    private static class SpaceMarineRowMapper implements RowMapper<SpaceMarine> {
        @Override
        public SpaceMarine mapRow(ResultSet rs, int rowNum) throws SQLException {
            var coordinates = new Coordinates(rs.getFloat("x"), rs.getInt("y"));
            var weapon = Weapon.valueOf(rs.getString("weapon_type"));

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
        }
    }
}
