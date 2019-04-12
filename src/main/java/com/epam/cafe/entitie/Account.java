package com.epam.cafe.entitie;

import java.math.BigDecimal;

public class Account {
    private int ID;
    private BigDecimal money;

    public Account(int ID, BigDecimal money) {
        this.ID = ID;
        this.money = money;
    }

    public int getID() {
        return ID;
    }

    public BigDecimal getMoney() {
        return money;
    }
}
