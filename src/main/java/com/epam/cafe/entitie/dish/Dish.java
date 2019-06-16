package com.epam.cafe.entitie.dish;

import java.io.Serializable;
import java.math.BigDecimal;

public class Dish implements Serializable {
    public static final String ID_COLUMN = "ID";
    public static final String NAME_COLUMN = "Name";
    public static final String DESCRIPTION_COLUMN = "Description";
    public static final String COST_COLUMN = "Cost";
    public static final String IMAGE_HREF_COLUMN = "ImageHref";
    public static final String IS_IN_MENU_COLUMN = "IsInMenu";

    private int ID;
    private String name;
    private String description;
    private BigDecimal cost;

    private String imageHref;

    private boolean isInMenu;

    public Dish(int ID, String name, String description, BigDecimal cost, String imageHref, boolean isInMenu) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.imageHref = imageHref;
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

    public String getImageHref() {
        return imageHref;
    }

    public boolean isInMenu() {
        return isInMenu;
    }
}
