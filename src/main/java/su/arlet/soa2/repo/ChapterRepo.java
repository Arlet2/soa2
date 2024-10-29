package su.arlet.soa2.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import su.arlet.soa2.core.Chapter;

@Service
public class ChapterRepo {
    @Autowired
    private JdbcTemplate template;

    public Chapter getByID(Long id) {
        var chapters = template.query(
                """
                                 SELECT * FROM chapters
                                 WHERE id = $1
                        """,
                (rs, rowNum) -> new Chapter(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("marines_count")),
                id
        );

        return chapters.getFirst();
    }

    public Long create(Chapter chapter) {
        return template.queryForObject(
                """
                                INSERT INTO chapters
                                (
                                    name, marines_count
                                )
                                VALUES
                                (
                                    $1, $2
                                )
                                RETURNING id
                        """,
                Long.class,
                chapter.getName(), chapter.getMarinesCount()
        );
    }
}
