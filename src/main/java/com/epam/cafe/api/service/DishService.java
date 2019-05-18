package com.epam.cafe.api.service;

import com.epam.cafe.entitie.Dish;
import com.epam.cafe.service.exception.ServiceException;

import java.util.List;

public interface DishService {
    List<Dish> getDishesInMenu() throws ServiceException;
}
