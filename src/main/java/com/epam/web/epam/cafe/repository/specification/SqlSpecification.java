package com.epam.web.epam.cafe.repository.specification;

import com.epam.web.epam.cafe.repository.exception.SqlConvertingException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface SqlSpecification {
    PreparedStatement toSqlClause(Connection connection) throws SqlConvertingException;
}
