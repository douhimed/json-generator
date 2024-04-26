package org.adex.inputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.adex.utils.*;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
public final class ObjectMetaData {
    private String name;
    private ObjectType type;
    private int min, max;
    private boolean upperCase;
    private Set<ObjectMetaData> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectMetaData that = (ObjectMetaData) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public Object generateValue() {
        return switch (type) {
            case TEXT -> TextUtils.generate(min, max, upperCase);
            case STRING -> StringUtils.generate(min, max, upperCase);
            case NUMBER -> NumberUtils.generate(min, max);
            case BOOLEAN -> BooleanUtils.GENERATE.get();
            case ARRAY, OBJECT -> null;
        };
    }

    public String build(boolean addColumnName) {
        String value = Objects.requireNonNull(generateValue()).toString();
        if(isStringOrDate()) {
            value = "\"" + value + "\"";
        }

        return addColumnName ? getName() + value : value;
    }

    public String getName() {
        return "\"" + name + "\":";
    }

    private boolean isStringOrDate() {
        return ObjectType.isDate(type) || ObjectType.isString(type);
    }
}
