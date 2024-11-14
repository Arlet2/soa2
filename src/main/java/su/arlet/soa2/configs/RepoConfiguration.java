package su.arlet.soa2.configs;


import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfiguration {

    @Bean
    public DSLContext dslContext() {
        return DSL.using(SQLDialect.POSTGRES);
    }
}
