package com.epam.web.epam.cafe.repository.impl;

import com.epam.web.epam.cafe.entitie.Account;
import com.epam.web.epam.cafe.entitie.user.User;
import com.epam.web.epam.cafe.entitie.user.UserRole;
import com.epam.web.epam.cafe.repository.Repository;
import com.epam.web.epam.cafe.repository.builder.EntityBuilder;
import com.epam.web.epam.cafe.repository.exception.QueryExecutionException;
import com.epam.web.epam.cafe.repository.exception.SqlConvertingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class UserRepository extends AbstractRepository<User> implements Repository<User> {
    private static final String SAVE_STATEMENT = "INSERT INTO user (Login, Password, Name, Surname, Role, Score, AccountId) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_STATEMENT = "DELETE FROM user WHERE ID = ?";

    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private static final int FOURTH = 4;
    private static final int FIFES = 5;
    private static final int SIXES = 6;
    private static final int SEVENS = 7;

    public UserRepository(Connection connection, EntityBuilder<User> builder) {
        super(connection, builder);
    }

    @Override
    public void save(User user) throws QueryExecutionException, SqlConvertingException {
        try {
            PreparedStatement statement = getConnection().prepareStatement(SAVE_STATEMENT);

            statement.setString(FIRST, user.getLogin());
            statement.setString(SECOND, user.getPassword());
            statement.setString(THIRD, user.getName());
            statement.setString(FOURTH, user.getSurname());

            UserRole role = user.getRole();
            statement.setString(FIFES, role.name());

            Integer score = user.getScore();
            if (score == null) {
                statement.setNull(SIXES, Types.INTEGER);
            } else {
                statement.setInt(SIXES, score);
            }

            Account account = user.getAccount();
            if (account == null) {
                statement.setNull(SEVENS, Types.INTEGER);
            } else {
                statement.setInt(SEVENS, account.getID());
            }

            execute(statement);
        } catch (SQLException e) {
            throw new SqlConvertingException("Error when converting save query.", e);
        }
    }

    @Override
    public void remove(User user) throws QueryExecutionException, SqlConvertingException {
        try {
            PreparedStatement statement = getConnection().prepareStatement(REMOVE_STATEMENT);
            statement.setInt(FIRST, user.getID());
            execute(statement);
        } catch (SQLException e) {
            throw new SqlConvertingException("Error when converting remove query.", e);
        }
    }
}
