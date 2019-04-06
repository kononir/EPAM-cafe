package com.epam.web.epam.cafe.repository.specification;

public interface EntitySpecification<T> {
    boolean specified(T specifiedElement);
}
