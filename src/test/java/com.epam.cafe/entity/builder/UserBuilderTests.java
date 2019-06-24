package com.epam.cafe.entity.builder;

import com.epam.cafe.api.EntityBuilder;
import com.epam.cafe.entitie.builder.UserBuilder;
import com.epam.cafe.entitie.Account;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.entitie.user.UserRole;
import com.epam.cafe.repository.RepositoryException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserBuilderTests {
    private static final int ID = 0;
    private static final String LOGIN = "kononir";
    private static final String PASSWORD = "03091999";
    private static final String NAME = "Vlad";
    private static final String SURNAME = "Novitskiy";

    private static final String ADMIN_ROLE = "ADMINISTRATOR";
    private static final Object ADMIN_SCORE = null;
    private static final Integer ADMIN_ACCOUNT_ID = 0;

    private static final String CLIENT_ROLE = "CLIENT";
    private static final Object CLIENT_SCORE = 0;
    private static final Object CLIENT_ACCOUNT_ID = 0;
    private static final BigDecimal CLIENT_ACCOUNT_MONEY = new BigDecimal(1000);

    @Test
    public void testBuildShouldReturnUserWithRoleAdministratorWhenGivenResultSetWithAdministrator()
            throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(User.ID_COLUMN)).thenReturn(ID);
        when(resultSet.getString(User.LOGIN_COLUMN)).thenReturn(LOGIN);
        when(resultSet.getString(User.PASSWORD_COLUMN)).thenReturn(PASSWORD);
        when(resultSet.getString(User.NAME_COLUMN)).thenReturn(NAME);
        when(resultSet.getString(User.SURNAME_COLUMN)).thenReturn(SURNAME);

        when(resultSet.getString(User.ROLE_COLUMN)).thenReturn(ADMIN_ROLE);
        when(resultSet.getObject(User.SCORE_COLUMN)).thenReturn(ADMIN_SCORE);
        when(resultSet.getObject(User.ACCOUNT_ID_COLUMN)).thenReturn(ADMIN_ACCOUNT_ID);

        EntityBuilder<User> builder = new UserBuilder();

        User user = builder.build(resultSet);

        Assert.assertEquals(ID, user.getID());
        Assert.assertEquals(LOGIN, user.getLogin());
        Assert.assertEquals(PASSWORD, user.getPassword());
        Assert.assertEquals(NAME, user.getName());
        Assert.assertEquals(SURNAME, user.getSurname());
        Assert.assertEquals(UserRole.valueOf(ADMIN_ROLE), user.getRole());
        Assert.assertEquals(ADMIN_SCORE, user.getScore());
        Assert.assertEquals(ADMIN_ACCOUNT_ID, (Integer) user.getAccountID());
    }

    @Test
    public void testBuildShouldReturnUserWithRoleClientWhenGivenResultSetWithClient()
            throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(User.ID_COLUMN)).thenReturn(ID);
        when(resultSet.getString(User.LOGIN_COLUMN)).thenReturn(LOGIN);
        when(resultSet.getString(User.PASSWORD_COLUMN)).thenReturn(PASSWORD);
        when(resultSet.getString(User.NAME_COLUMN)).thenReturn(NAME);
        when(resultSet.getString(User.SURNAME_COLUMN)).thenReturn(SURNAME);

        when(resultSet.getString(User.ROLE_COLUMN)).thenReturn(CLIENT_ROLE);
        when(resultSet.getObject(User.SCORE_COLUMN)).thenReturn(CLIENT_SCORE);
        when(resultSet.getObject(User.ACCOUNT_ID_COLUMN)).thenReturn(CLIENT_ACCOUNT_ID);
        when(resultSet.getBigDecimal(Account.MONEY_COLUMN)).thenReturn(CLIENT_ACCOUNT_MONEY);

        EntityBuilder<User> builder = new UserBuilder();

        User user = builder.build(resultSet);

        Assert.assertEquals(ID, user.getID());
        Assert.assertEquals(LOGIN, user.getLogin());
        Assert.assertEquals(PASSWORD, user.getPassword());
        Assert.assertEquals(NAME, user.getName());
        Assert.assertEquals(SURNAME, user.getSurname());
        Assert.assertEquals(UserRole.valueOf(CLIENT_ROLE), user.getRole());
        Assert.assertEquals(CLIENT_SCORE, user.getScore());

        Assert.assertEquals(CLIENT_ACCOUNT_ID, user.getAccountID());
    }
}
