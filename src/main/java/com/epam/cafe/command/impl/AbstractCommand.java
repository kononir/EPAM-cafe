package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.DishService;
import com.epam.cafe.api.service.UserService;
import com.epam.cafe.api.util.BonusHelper;
import com.epam.cafe.api.util.DishHelper;
import com.epam.cafe.api.util.OrderHelper;
import com.epam.cafe.api.util.UserHelper;
import com.epam.cafe.entitie.Bonus;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.exception.ServiceException;
import com.epam.cafe.util.BonusHelperImpl;
import com.epam.cafe.util.DishHelperImpl;
import com.epam.cafe.util.OrderHelperImpl;
import com.epam.cafe.util.UserHelperImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCommand implements Command {
    private UserHelper userHelper = new UserHelperImpl();
    private DishHelper dishHelper = new DishHelperImpl();
    private BonusHelper bonusHelper = new BonusHelperImpl();
    private OrderHelper orderHelper = new OrderHelperImpl();

    protected User findClient(HttpServletRequest request) {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<User> clients = (ArrayList<User>) session.getAttribute("clients");
        Integer clientID = Integer.valueOf(request.getParameter("clientID"));

        return userHelper.findUserByID(clients, clientID);
    }

    protected Bonus findBonus(HttpServletRequest request) {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Bonus> bonuses = (ArrayList<Bonus>) session.getAttribute("clientBonuses");
        int bonusID = Integer.parseInt(request.getParameter("bonusID"));

        return bonusHelper.findBonusById(bonuses, bonusID);
    }

    protected Dish findDish(HttpServletRequest request) {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Dish> dishes = (ArrayList<Dish>) session.getAttribute("allDishes");
        int bonusID = Integer.parseInt(request.getParameter("dishID"));

        return dishHelper.findDishById(dishes, bonusID);
    }

    protected Order findOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        return orderHelper.findOrderById(orders, orderID);
    }

    protected void addDishToRequest(HttpServletRequest request, Dish dish) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Dish> dishes = (ArrayList<Dish>) session.getAttribute("allDishes");
        dishes.add(dish);
    }

    protected void removeBonusFromRequest(HttpServletRequest request, Bonus bonus) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Bonus> bonuses = (ArrayList<Bonus>) session.getAttribute("clientBonuses");
        bonuses.remove(bonus);
    }

    protected void removeDishFromRequest(HttpServletRequest request, Dish dish) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Dish> dishes = (ArrayList<Dish>) session.getAttribute("allDishes");
        dishes.remove(dish);
    }

    protected void calculateCostsOfDishesInBasket(HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> order = (HashMap<Integer, Integer>) session.getAttribute("order");
        @SuppressWarnings("unchecked")
        List<Dish> dishes = (ArrayList<Dish>) session.getAttribute("dishes");

        Map<Integer, BigDecimal> generalCosts = dishHelper.calculateGeneralCosts(dishes, order);
        session.setAttribute("generalCosts", generalCosts);

        BigDecimal result = dishHelper.calculateResult(generalCosts);
        session.setAttribute("resultCost", result);
    }

    protected Map<Integer, Dish> getDishesFromOrders(List<Order> orders, DishService service)
            throws ServiceException {
        List<Integer> dishIDs = dishHelper.getDistinctDishesIdsOfOrders(orders);
        List<Dish> dishes = service.getDishesByIds(dishIDs);

        return dishHelper.convertDishesToIdDishMap(dishes);
    }

    protected Map<Integer, User> getUsersFromOrders(List<Order> orders, UserService service)
            throws ServiceException {
        List<Integer> userIDs = userHelper.getDistinctUserIdsOfOrders(orders);
        List<User> users = service.getUsersByIds(userIDs);

        return userHelper.convertUsersToIdUserMap(users);
    }
}
