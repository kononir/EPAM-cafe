package com.epam.cafe.entitie;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<Dish> chosenDishes;
    private LocalDateTime receiptTime;

    private Integer score;
    private String comment;

    public Order(List<Dish> chosenDishes, LocalDateTime receiptTime, Integer score, String comment) {
        this.chosenDishes = chosenDishes;
        this.receiptTime = receiptTime;
        this.score = score;
        this.comment = comment;
    }

    public List<Dish> getChosenDishes() {
        return chosenDishes;
    }

    public LocalDateTime getReceiptTime() {
        return receiptTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
