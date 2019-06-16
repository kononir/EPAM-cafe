package com.epam.cafe.api.util;

import com.epam.cafe.entitie.dish.Dish;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DishHelper {
    Map<Integer, BigDecimal> calculateGeneralCosts(List<Dish> dishes, Map<Integer, Integer> order);

    BigDecimal calculateResult(Map<Integer, BigDecimal> generalCosts);
}
