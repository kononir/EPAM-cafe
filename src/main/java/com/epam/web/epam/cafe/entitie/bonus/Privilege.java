package com.epam.web.epam.cafe.entitie.bonus;

public class Privilege {
    private PrivilegeType type;
    private String name;
    private String description;

    public Privilege(PrivilegeType type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public PrivilegeType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
