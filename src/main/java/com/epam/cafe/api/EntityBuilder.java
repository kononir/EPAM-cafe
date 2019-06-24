package com.epam.cafe.api;

import com.epam.cafe.repository.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityBuilder<T> {
    T build(ResultSet resultSet) throws SQLException, RepositoryException;
}
