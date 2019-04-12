package com.epam.cafe.api;

import com.epam.cafe.repository.exception.RepositoryException;

import java.util.List;

public interface Repository<T> {
    void save(T element) throws RepositoryException;

    void remove(T element) throws RepositoryException;

    List<T> query(GeneralSpecification<T> entitySpecification) throws RepositoryException;
}
