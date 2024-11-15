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
import su.arlet.soa2.core.Chapter;
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



    public SpaceMarine getByID(Long id) {
        var query = (dsl.select().from(table("space_marines")).join(table("chapters")).on("space_marines.chapter_id=chapters.id").where(field("space_marines.id").eq(id)).getSQL(ParamType.INLINED));
        return template.query(
                query,
                new SpaceMarineRowMapper()
        ).getFirst();
    }

    public void deleteByID(Long id) {
        var query = dsl.deleteFrom(table("space_marines")).where(field("id").eq(id)).getSQL(ParamType.INLINED);
        template.update(query);
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
                spaceMarine.getAchievements(), spaceMarine.getWeaponType().toString(), spaceMarine.getChapter().getId()
        );
    }

    public Page<SpaceMarine> findAll(int page, int size, Collection<SortField<Object>> sortBy, Condition condition) {
        PageRequest pageRequest = PageRequest.of(page, size);

        String query = dsl.select().from(table("space_marines")).join(table("chapters")).on("space_marines.chapter_id=chapters.id").where(condition).orderBy(sortBy).limit(size).offset(page*size).getSQL(ParamType.INLINED);
        System.out.println(query);
        List<SpaceMarine> spaceMarines = template.query(query, new SpaceMarineRowMapper());

        long total = template.queryForObject("SELECT count(*) FROM space_marines", Long.class);

        return new PageImpl<>(spaceMarines, pageRequest, total);
    }

    public SpaceMarine updatePatch(SpaceMarine spaceMarine) {
        var query = dsl.update(table("space_marines"))
                .set(field("name"), spaceMarine.getName())
                .set(field("x"), spaceMarine.getCoordinates().getX())
                .set(field("y"), spaceMarine.getCoordinates().getY())
                .set(field("creation_date"), Timestamp.valueOf(spaceMarine.getCreationDate()))
                .set(field("health"), spaceMarine.getHealth())
                .set(field("heart_count"), spaceMarine.getHeartCount())
                .set(field("achievements"), spaceMarine.getAchievements())
                .set(field("weapon_type"), spaceMarine.getWeaponType().toString())
                .set(field("chapter_id"), spaceMarine.getChapter().getId())
                .where(field("id").eq(spaceMarine.getId()))
                .getSQL(ParamType.INLINED);

        template.update(query);

        return spaceMarine;
    }

    private static class SpaceMarineRowMapper implements RowMapper<SpaceMarine> {
        @Override
        public SpaceMarine mapRow(ResultSet rs, int rowNum) throws SQLException {
            var coordinates = new Coordinates(rs.getFloat("x"), rs.getInt("y"));
            var weapon = Weapon.valueOf(rs.getString("weapon_type"));
            var chapter = new Chapter(rs.getLong("chapter_id"), rs.getString("name"), rs.getInt("marines_count"));

            return new SpaceMarine(
                    rs.getInt("id"),
                    rs.getString("name"),
                    coordinates,
                    rs.getTimestamp("creation_date").toLocalDateTime(),
                    rs.getInt("health"),
                    rs.getLong("heart_count"),
                    rs.getString("achievements"),
                    weapon,
                    chapter
            );
        }
    }
}
