package com.epam.cafe.repository.impl;

import com.epam.cafe.api.builder.EntityBuilder;
import com.epam.cafe.api.builder.QueryBuilder;
import com.epam.cafe.api.repository.Repository;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.query.DeleteQueryBuilder;
import com.epam.cafe.query.InsertQueryBuilder;
import com.epam.cafe.query.UpdateQueryBuilder;
import com.epam.cafe.repository.exception.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<T> implements Repository<T> {
    private static final String ID_COLUMN = "ID";

    private Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(T element) throws RepositoryException {
        try {
            Map<String, Object> paramsMap = getParams(element);
            List<String> params = new ArrayList<>(paramsMap.keySet());
            List<Object> values = new ArrayList<>(paramsMap.values());
            
            QueryBuilder queryBuilder = new InsertQueryBuilder();
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
            
            QueryBuilder queryBuilder = new UpdateQueryBuilder();
            PreparedStatement statement = makeStatementWithParams(queryBuilder, params, values);

            int whereParamIndex = params.size() + 1;
            Object whereValue = paramsMap.get(ID_COLUMN);
            statement.setObject(whereParamIndex, whereValue);

            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Repository updating error.", e);
        }
    }
    
    private PreparedStatement makeStatementWithParams(QueryBuilder queryBuilder, List<String> params,
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
            String query = queryBuilder.build(getTableName(), Collections.emptyList());

            PreparedStatement statement = connection.prepareStatement(query);

            int whereParamIndex = 0;
            Map<String, Object> paramsMap = getParams(element);
            Object whereValue = paramsMap.get(ID_COLUMN);
            statement.setObject(whereParamIndex, whereValue);

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

    protected abstract String getTableName();

    protected abstract Map<String, Object> getParams(T entity);
}
