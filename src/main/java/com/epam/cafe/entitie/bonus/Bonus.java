package com.epam.cafe.entitie.bonus;

import java.io.Serializable;

public class Bonus implements Serializable {
    public static final String ID_COLUMN = "ID";
    public static final String NAME_COLUMN = "Name";
    public static final String DESCRIPTION_COLUMN = "Description";
    public static final String USER_ID_COLUMN = "UserID";

    private int ID;
    private String name;
    private String description;
    private int userID;

    public Bonus(int ID, String name, String description, int userID) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.userID = userID;
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

    public int getUserID() {
        return userID;
    }
}
