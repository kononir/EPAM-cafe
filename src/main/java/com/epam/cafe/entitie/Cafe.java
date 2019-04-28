package com.epam.cafe.entitie;

import com.epam.cafe.entitie.user.User;

import java.util.List;
import java.util.Map;

public class Cafe {
    private List<Dish> menu;
    private Map<User, List<Bonus>> bonuses;

    public Cafe(List<Dish> menu, Map<User, List<Bonus>> privileges) {
        this.menu = menu;
        this.bonuses = privileges;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public Map<User, List<Bonus>> getBonuses() {
        return bonuses;
    }

    public void setBonuses(Map<User, List<Bonus>> bonuses) {
        this.bonuses = bonuses;
    }
}
