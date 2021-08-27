package com.redistest.demo.model;

public enum CartStatus {
    ACTIVE ("ACTIVE"),
    COMPLETED("COMPLETED");

    private final String name;

    private CartStatus(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
