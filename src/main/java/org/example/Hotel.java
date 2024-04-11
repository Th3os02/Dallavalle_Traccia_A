package org.example;

public class Hotel {
    private String name;
    private boolean hasSpa;

    public Hotel(String name, boolean hasSpa) {
        this.name = name;
        this.hasSpa = hasSpa;
    }

    public String getName() {
        return name;
    }

    public boolean hasSpa() {
        return hasSpa;
    }
}