package com.epam.cafe.entitie;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ID_COLUMN = "ID";
    public static final String MONEY_COLUMN = "Money";

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

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
