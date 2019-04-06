package com.epam.web.epam.cafe.repository.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EntityBuilder<T> {
    List<T> build(ResultSet resultSet) throws SQLException;
}
