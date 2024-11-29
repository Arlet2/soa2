package su.arlet.soa2.repo;


import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.conf.ParamType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import su.arlet.soa2.core.Chapter;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Service
@AllArgsConstructor
public class SpaceshipRepo {
    private final DSLContext dsl;
    private JdbcTemplate template;

    public Long createSpaceship(String name) {
        String sql = dsl.insertInto(table("spaceships"), field("spaceship_name")).values(name).returning(field("spaceship_id")).getSQL(ParamType.INLINED);
        return template.queryForObject(sql, Long.class);
    }

    public void undeployAllSpaceMarines(Long id) {
        String sql = dsl.update(table("space_marines")).set(field("spaceship_id"), (Object) null).where(field("spaceship_id").eq(id)).getSQL(ParamType.INLINED);
        template.execute(sql);
    }

}
