package com.epam.web.epam.cafe.repository;

import com.epam.web.epam.cafe.repository.specification.Specification;

import java.util.List;

public interface Repository<T> {
    void add(T element);
    void update(T element);
    void remove(T element);

    List<T> query(Specification<T> specification);
}
