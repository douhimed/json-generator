package org.adex.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.adex.utils.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
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

    public boolean isObject() {
        return Optional.ofNullable(type)
                .map(ObjectType::isObject)
                .orElseThrow(() -> new IllegalStateException("Type must be defined"));
    }

    public boolean isLeaf() {
        return Optional.ofNullable(type)
                .map(ObjectType::isLeaf)
                .orElseThrow(() -> new IllegalStateException("Type must be defined"));
    }

    public Object generateValue() {
        return switch (type) {
            case TEXT -> TextUtils.generate(min, max, upperCase);
            case STRING -> StringUtils.generate(min, max, upperCase);
            case NUMBER -> NumberUtils.generate(min, max);
            case BOOLEAN -> BooleanUtils.generate();
            case ARRAY -> ArrayUtils.generate(min, max, getChildren().iterator().next());
            case OBJECT -> null;
        };
    }

}
