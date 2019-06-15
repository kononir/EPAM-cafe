package com.epam.cafe.entitie.order;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {
    public static final String ID_COLUMN = "ID";
    public static final String RECEIPT_TIME_COLUMN = "ReceiptTime";
    public static final String SCORE_COLUMN = "Score";
    public static final String COMMENT_COLUMN = "Comment";
    public static final String USER_ID_COLUMN = "UserID";

    private int ID;

    private Map<Integer, Integer> chosenDishes;
    private LocalDateTime receiptTime;

    private Integer score;
    private String comment;

    private int userID;

    public Order(Map<Integer, Integer> chosenDishes, LocalDateTime receiptTime, int userID) {
        this.chosenDishes = chosenDishes;
        this.receiptTime = receiptTime;
        this.userID = userID;
    }

    public Order(int ID, Map<Integer, Integer> chosenDishes, LocalDateTime receiptTime,
                 Integer score, String comment, int userID) {
        this.ID = ID;
        this.chosenDishes = chosenDishes;
        this.receiptTime = receiptTime;
        this.score = score;
        this.comment = comment;
        this.userID = userID;
    }

    public int getID() {
        return ID;
    }

    public Map<Integer, Integer> getChosenDishes() {
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

    public int getUserID() {
        return userID;
    }
}
