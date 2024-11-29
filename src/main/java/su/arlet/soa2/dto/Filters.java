package su.arlet.soa2.dto;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filters {

    private static final Pattern FILTER_PATTERN = Pattern.compile("^([а-яА-Яa-zA-Z_]+)([><=]|>=|<=)([а-яА-Яa-zA-Z0-9]+)$");
    private List<Condition> predicates = new LinkedList<>();

    public Filters(String[] filters)  {
        for (String filter : filters) {
            Matcher matcher = FILTER_PATTERN.matcher(filter);
            if (matcher.matches()) {
                String fieldName = matcher.group(1);
                String operator = matcher.group(2);
                String value = matcher.group(3);

                Field<Object> field = DSL.field(DSL.name(fieldName));
                Condition condition = createCondition(field, operator, value);
                predicates.add(condition);
            } else {
                throw new IllegalArgumentException("Invalid filter format: " + filter);
            }
        }
    }

    private Condition createCondition(Field<Object> field, String operator, String value)  throws IllegalArgumentException  {
        switch (operator) {
            case ">":
                return field.gt(value);
            case "<":
                return field.lt(value);
            case "=":
                return field.eq(value);
            case ">=":
                return field.ge(value);
            case "<=":
                return field.le(value);
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    public List<Condition> getPredicates() {
        return predicates;
    }
}