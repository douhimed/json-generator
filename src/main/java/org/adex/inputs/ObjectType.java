package org.adex.inputs;

public enum ObjectType {
    STRING, NUMBER, BOOLEAN, TEXT, OBJECT, ARRAY, DATE;

    public static boolean isLeaf(ObjectType type) {
        return OBJECT != type && ARRAY != type;
    }

    public static boolean isArray(ObjectType type) {
        return ARRAY == type;
    }

    public static boolean isDate(ObjectType type) {
        return type == DATE;
    }

    public static boolean isString(ObjectType type) {
        return STRING == type;

    }
}
