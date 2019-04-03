package com.epam.web.epam.cafe.entitie;

import java.math.BigDecimal;

public class Dish {
    private int ID;
    private String name;
    private String description;
    private BigDecimal cost;

    private boolean isInMenu;

    public Dish(int ID, String name, String description, BigDecimal cost, boolean isInMenu) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.isInMenu = isInMenu;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public boolean isInMenu() {
        return isInMenu;
    }
}
