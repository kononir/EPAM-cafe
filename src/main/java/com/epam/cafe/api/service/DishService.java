package com.epam.cafe.api.service;

import com.epam.cafe.entitie.dish.Dish;
import com.epam.cafe.service.exception.ServiceException;

import java.util.List;

public interface DishService {
    List<Dish> getDishesInMenu() throws ServiceException;

    List<Dish> getDishesByIds(List<Integer> dishesIDs) throws ServiceException;
}
