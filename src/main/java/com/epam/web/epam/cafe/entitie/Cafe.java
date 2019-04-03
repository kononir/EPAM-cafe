package com.epam.web.epam.cafe.entitie;

import com.epam.web.epam.cafe.entitie.bonus.Privilege;
import com.epam.web.epam.cafe.entitie.user.User;

import java.util.List;
import java.util.Map;

public class Cafe {
    private List<Dish> menu;
    private Map<User, List<Privilege>> privileges;

    public Cafe(List<Dish> menu, Map<User, List<Privilege>> privileges) {
        this.menu = menu;
        this.privileges = privileges;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public Map<User, List<Privilege>> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Map<User, List<Privilege>> privileges) {
        this.privileges = privileges;
    }
}
