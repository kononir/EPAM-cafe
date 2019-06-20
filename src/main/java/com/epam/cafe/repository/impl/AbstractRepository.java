package com.epam.cafe.repository.impl;

import com.epam.cafe.api.entity.EntityBuilder;
import com.epam.cafe.api.query.QueryBuilder;
import com.epam.cafe.api.query.QueryBuilderWithParams;
import com.epam.cafe.api.repository.Repository;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.query.DeleteQueryBuilder;
import com.epam.cafe.query.InsertQueryBuilder;
import com.epam.cafe.query.UpdateQueryBuilder;
import com.epam.cafe.repository.exception.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<T> implements Repository<T> {
    private static final String ID_COLUMN = "ID";
    private static final int ID_COLUMN_INDEX = 1;

    private Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void save(T element) throws RepositoryException {
        try {
            Map<String, Object> paramsMap = getParams(element);
            paramsMap.remove(ID_COLUMN);

            List<String> params = new ArrayList<>(paramsMap.keySet());
            List<Object> values = new ArrayList<>(paramsMap.values());

            QueryBuilderWithParams queryBuilder = new InsertQueryBuilder();
            PreparedStatement statement = makeStatementWithParams(queryBuilder, params, values);

            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Saving to repository error.", e);
        }
    }

    @Override
    public void update(T element) throws RepositoryException {
        try {
            Map<String, Object> paramsMap = getParams(element);
            List<String> params = new ArrayList<>(paramsMap.keySet());
            List<Object> values = new ArrayList<>(paramsMap.values());

            QueryBuilderWithParams queryBuilder = new UpdateQueryBuilder();
            PreparedStatement statement = makeStatementWithParams(queryBuilder, params, values);

            int whereParamIndex = params.size() + 1;
            Object whereValue = paramsMap.get(ID_COLUMN);
            statement.setObject(whereParamIndex, whereValue);

            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Repository updating error.", e);
        }
    }

    private PreparedStatement makeStatementWithParams(QueryBuilderWithParams queryBuilder,
                                                      List<String> params,
                                                      List<Object> values) throws SQLException {
        String query = queryBuilder.build(getTableName(), params);
        PreparedStatement statement = connection.prepareStatement(query);

        for (int i = 0; i < values.size(); i++) {
            statement.setObject(i + 1, values.get(i));
        }

        return statement;
    }

    @Override
    public void remove(T element) throws RepositoryException {
        try {
            QueryBuilder queryBuilder = new DeleteQueryBuilder();
            String query = queryBuilder.build(getTableName());

            PreparedStatement statement = connection.prepareStatement(query);

            Map<String, Object> paramsMap = getParams(element);
            Object idValue = paramsMap.get(ID_COLUMN);
            statement.setObject(ID_COLUMN_INDEX, idValue);

            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Removing from repository error.", e);
        }
    }

    protected List<T> executeQuery(SqlSpecification specification, EntityBuilder<T> builder)
            throws RepositoryException {
        List<T> builtEntities = new ArrayList<>();

        try {
            String query = specification.toSqlClause();
            PreparedStatement statement = connection.prepareStatement(query);
            List<Object> params = specification.getParams();
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                builtEntities.add(entity);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error when execute searching query.", e);
        }

        return builtEntities;
    }

    protected int getLastID() throws SQLException {
        Statement lastIDStatement = connection.createStatement();
        String query = "SELECT MAX(ID) FROM " + getTableName();
        ResultSet resultSet = lastIDStatement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt(ID_COLUMN_INDEX);
    }

    protected abstract String getTableName();

    protected abstract Map<String, Object> getParams(T entity);
}
