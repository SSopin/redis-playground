package com.redistest.demo.model;

public enum NumberGenerationType {
    LOOP ("LOOP"),
    LIST("LIST"),
    SET("SET");

    private final String name;

    private NumberGenerationType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
