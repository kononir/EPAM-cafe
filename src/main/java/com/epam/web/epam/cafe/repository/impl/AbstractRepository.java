package com.epam.web.epam.cafe.repository.impl;

import com.epam.web.epam.cafe.repository.Repository;
import com.epam.web.epam.cafe.repository.builder.EntityBuilder;
import com.epam.web.epam.cafe.repository.exception.QueryExecutionException;
import com.epam.web.epam.cafe.repository.exception.SqlConvertingException;
import com.epam.web.epam.cafe.repository.specification.GeneralSpecification;

import java.sql.*;
import java.util.List;

public abstract class AbstractRepository<T> implements Repository<T> {
    private Connection connection;
    private EntityBuilder<T> builder;

    public AbstractRepository(Connection connection, EntityBuilder<T> builder) {
        this.connection = connection;
        this.builder = builder;
    }

    public Connection getConnection() {
        return connection;
    }

    protected List<T> executeQuery(PreparedStatement statement) throws QueryExecutionException {
        List<T> builtEntities;

        try {
            ResultSet resultSet = statement.executeQuery();

            builtEntities = builder.build(resultSet);
        } catch (SQLException e) {
            throw new QueryExecutionException("Error when execute searching query.", e);
        }

        return builtEntities;
    }

    protected void execute(PreparedStatement statement) throws QueryExecutionException {
        try {
            statement.execute();
        } catch (SQLException e) {
            throw new QueryExecutionException("Error when execute modification query.", e);
        }
    }

    @Override
    public List<T> query(GeneralSpecification<T> specification)
            throws QueryExecutionException, SqlConvertingException {
        PreparedStatement statement = specification.toSqlClause(connection);
        return executeQuery(statement);
    }
}
