package com.epam.cafe.builder;

import com.epam.cafe.api.builder.EntityBuilder;
import com.epam.cafe.entitie.Bonus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BonusBuilder implements EntityBuilder<Bonus> {

    @Override
    public Bonus build(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt(Bonus.ID_COLUMN);
        String name = resultSet.getString(Bonus.NAME_COLUMN);
        String description = resultSet.getString(Bonus.DESCRIPTION_COLUMN);
        int userID = resultSet.getInt(Bonus.USER_ID_COLUMN);

        return new Bonus(ID, name, description, userID);
    }
}
