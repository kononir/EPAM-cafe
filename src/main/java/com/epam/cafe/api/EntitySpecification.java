package com.epam.cafe.api;

public interface EntitySpecification<T> {
    boolean specified(T specifiedElement);
}
