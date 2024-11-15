package su.arlet.soa2.repo;

import lombok.*;
import org.jooq.DSLContext;
import org.jooq.conf.ParamType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import su.arlet.soa2.core.Chapter;


import java.util.HashMap;
import java.util.Optional;

import static org.jooq.impl.DSL.*;


@AllArgsConstructor
@Service
public class ChapterRepo {

    private NamedParameterJdbcTemplate template;
    private DSLContext dsl;



    public Chapter getByID(Long id) {
        return template.query(
                dsl.select().from(table("chapters")).where(field("id").eq(id)).getSQL(ParamType.INLINED),
                (rs, rowNum) -> new Chapter(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("marines_count"))

        ).getFirst();
    }

    public Long createChapter(Chapter chapter) {
        return template.query(
                dsl.insertInto(table("chapters")).columns(field("name"), field("marines_count"))
                        .values(chapter.getName(), chapter.getMarinesCount()).returning(field("id")).getSQL(ParamType.INLINED),
                (rs, rowNum) -> rs.getLong("id")
        ).getFirst();
    }

    public Optional<Chapter> findByName(String name) {
        return template.query(
                dsl.select().from(table("chapters")).where(field("name").eq(name)).getSQL(ParamType.INLINED),
                (rs, rowNum) -> new Chapter(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("marines_count"))
        ).stream().findFirst();
    }

}
