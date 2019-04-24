package com.epam.cafe.entitie.bonus;

public class Privilege {
    private String name;
    private String description;

    public Privilege(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
