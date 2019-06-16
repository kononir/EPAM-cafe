package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.util.BonusHelper;
import com.epam.cafe.api.util.DishHelper;
import com.epam.cafe.api.util.UserHelper;
import com.epam.cafe.entitie.bonus.Bonus;
import com.epam.cafe.entitie.dish.Dish;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.util.BonusHelperImpl;
import com.epam.cafe.util.DishHelperImpl;
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

    protected void removeBonusFromRequest(HttpServletRequest request, Bonus bonus) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Bonus> bonuses = (ArrayList<Bonus>) session.getAttribute("clientBonuses");
        bonuses.remove(bonus);
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
}
