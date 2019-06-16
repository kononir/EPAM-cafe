package com.epam.cafe.service;

import com.epam.cafe.api.repository.Repository;
import com.epam.cafe.api.service.DishService;
import com.epam.cafe.entitie.dish.Dish;
import com.epam.cafe.repository.exception.RepositoryException;
import com.epam.cafe.repository.factory.RepositoryFactory;
import com.epam.cafe.repository.specification.dish.DishByIDsSpecification;
import com.epam.cafe.repository.specification.dish.DishByInMenuSpecification;
import com.epam.cafe.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class DishServiceImpl implements DishService {

    @Override
    public List<Dish> getDishesInMenu() throws ServiceException {
        List<Dish> dishesInMenu;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            dishesInMenu = dishRepository.query(new DishByInMenuSpecification(true));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting dishes in menu error.", e);
        }

        return dishesInMenu;
    }

    @Override
    public List<Dish> getDishesByIds(List<Integer> dishesIDs) throws ServiceException {
        List<Dish> dishes = new ArrayList<>();

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Dish> dishRepository = factory.dishRepository();
            for (Integer dishesId : dishesIDs) {
                dishes.addAll(dishRepository.query(new DishByIDsSpecification(dishesId)));
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Getting dishes by ids error.", e);
        }

        return dishes;
    }
}
