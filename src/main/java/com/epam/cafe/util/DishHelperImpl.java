package com.epam.cafe.util;

import com.epam.cafe.api.util.DishHelper;
import com.epam.cafe.entitie.Dish;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishHelperImpl implements DishHelper {

    @Override
    public Map<Integer, BigDecimal> calculateGeneralCosts(
            List<Dish> dishes,
            Map<Integer, Integer> order) {
        Map<Integer, BigDecimal> generalCosts = new HashMap<>();

        for (Dish dish : dishes) {
            Integer dishID = dish.getID();
            BigDecimal servingsNumber = BigDecimal.valueOf(order.get(dishID));
            BigDecimal dishCost = dish.getCost();
            BigDecimal generalCost = dishCost.multiply(servingsNumber);

            generalCosts.put(dishID, generalCost);
        }

        return generalCosts;
    }

    @Override
    public BigDecimal calculateResult(Map<Integer, BigDecimal> generalCosts) {
        BigDecimal result = new BigDecimal(0);
        for (BigDecimal generalCost : generalCosts.values()) {
            result = result.add(generalCost);
        }

        return result;
    }
}
