package com.epam.web.epam.cafe.repository;

import com.epam.web.epam.cafe.repository.exception.QueryExecutionException;
import com.epam.web.epam.cafe.repository.exception.SqlConvertingException;
import com.epam.web.epam.cafe.repository.specification.GeneralSpecification;

import java.util.List;

public interface Repository<T> {
    void save(T element) throws QueryExecutionException, SqlConvertingException;

    void remove(T element) throws QueryExecutionException, SqlConvertingException;

    List<T> query(GeneralSpecification<T> entitySpecification) throws QueryExecutionException, SqlConvertingException;
}
