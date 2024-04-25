package org.adex.services;

public enum ObjectType {
    STRING, NUMBER, BOOLEAN, TEXT, OBJECT, ARRAY;

    public boolean isObject() {
        return this == OBJECT;
    }

    public boolean isLeaf() {
        return !isObject();
    }
}
