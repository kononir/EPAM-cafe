package com.epam.cafe.api.builder;

import com.epam.cafe.repository.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityBuilder<T> {
    T build(ResultSet resultSet) throws SQLException, RepositoryException;
}
