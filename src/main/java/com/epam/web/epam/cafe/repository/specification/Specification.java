package com.epam.web.epam.cafe.repository.specification;

public interface Specification<T> {
    boolean specified(T specifiedElement);
}
