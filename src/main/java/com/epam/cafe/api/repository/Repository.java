package com.epam.cafe.api.repository;

import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.repository.exception.RepositoryException;

import java.util.List;

public interface Repository<T> {
    void save(T element) throws RepositoryException;

    void update(T element) throws RepositoryException;

    void remove(T element) throws RepositoryException;

    List<T> query(SqlSpecification specification) throws RepositoryException;
}
