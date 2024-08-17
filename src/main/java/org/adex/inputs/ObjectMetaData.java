package org.adex.inputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.adex.utils.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
public final class ObjectMetaData {
    private String name;
    private ObjectType type;
    private int min;
    private int max;
    private boolean upperCase;
    private Set<ObjectMetaData> children;
    private Object defaultValue;

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
        if (Objects.nonNull(defaultValue) && ObjectType.isLeaf(type)) {
            return defaultValue;
        }

        return switch (type) {
            case TEXT -> TextUtils.generate(min, max, upperCase);
            case STRING -> StringUtils.generate(min, max, upperCase);
            case NUMBER -> NumberUtils.generate(min, max);
            case BOOLEAN -> BooleanUtils.GENERATE.getAsBoolean();
            case ARRAY, OBJECT, DATE -> null;
        };
    }

    public String build(boolean addColumnName) {
        String value = Objects.requireNonNull(generateValue()).toString();
        if(isStringOrDate()) {
            value = String.format("\"%s\"", value);
        }

        return addColumnName ? getName() + value : value;
    }

    public String getName() {
       return String.format("\"%s\"%s", name, StringUtils.COLON);
    }

    private boolean isStringOrDate() {
        return ObjectType.isDate(type) || ObjectType.isString(type);
    }
}
