package com.epam.cafe.repository.impl;

import com.epam.cafe.api.EntityBuilder;
import com.epam.cafe.api.GeneralSpecification;
import com.epam.cafe.api.QueryBuilder;
import com.epam.cafe.api.Repository;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.repository.exception.RepositoryException;
import com.epam.cafe.query.DeleteQueryBuilder;
import com.epam.cafe.query.InsertQueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<T> implements Repository<T> {
    private Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(T element) throws RepositoryException {
        try {
            Map<String, Object> paramsMap = getParams(element);

            QueryBuilder queryBuilder = new InsertQueryBuilder();
            String query = queryBuilder.build(getTableName(), new ArrayList<>(paramsMap.keySet()));
            PreparedStatement statement = connection.prepareStatement(query);

            List<Object> params = new ArrayList<>(paramsMap.values());
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Query executing error.", e);
        }
    }

    @Override
    public void remove(T element) throws RepositoryException {
        try {
            QueryBuilder queryBuilder = new DeleteQueryBuilder();
            String query = queryBuilder.build(getTableName(), Collections.singletonList(User.ID_COLUMN));

            Map<String, Object> paramsMap = getParams(element);
            Object paramValue = paramsMap.get(User.ID_COLUMN);

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(0, paramValue);

            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Query executing error.", e);
        }
    }

    protected List<T> executeQuery(GeneralSpecification<T> specification, EntityBuilder<T> builder)
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
