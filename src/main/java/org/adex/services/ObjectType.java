package org.adex.services;

public enum ObjectType {
    STRING, NUMBER, BOOLEAN, TEXT, OBJECT, ARRAY;

    public static boolean isLeaf(ObjectType type) {
        return OBJECT != type && ARRAY != type;
    }

    public static boolean isArray(ObjectType type) {
        return ARRAY == type;
    }
}
