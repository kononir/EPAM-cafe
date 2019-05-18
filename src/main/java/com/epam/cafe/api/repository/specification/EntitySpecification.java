package com.epam.cafe.api.repository.specification;

public interface EntitySpecification<T> {
    boolean specified(T specifiedElement);
}
