package com.epam.cafe.entitie.user;

import com.epam.cafe.entitie.Account;

import java.io.Serializable;

public class User implements Serializable {
    public static final String ID_COLUMN = "ID";
    public static final String LOGIN_COLUMN = "Login";
    public static final String PASSWORD_COLUMN = "Password";
    public static final String NAME_COLUMN = "Name";
    public static final String SURNAME_COLUMN = "Surname";
    public static final String ROLE_COLUMN = "Role";
    public static final String SCORE_COLUMN = "Score";
    public static final String ACCOUNT_ID_COLUMN = "AccountID";

    private int ID;
    private String login;
    private String password;
    private String name;
    private String surname;
    private UserRole role;

    private Integer score;

    private Account account;

    public User(int ID, String login, String password, String name, String surname,
                Integer score, UserRole role, Account account) {
        this.ID = ID;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.score = score;
        this.role = role;
        this.account = account;
    }

    public int getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public UserRole getRole() {
        return role;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return ID == user.ID &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                role == user.role &&
                account.equals(user.account);
    }

    @Override
    public int hashCode() {
        return 31 * ID + (login == null ? 0 : login.hashCode())
                + (password == null ? 0 : password.hashCode())
                + (name == null ? 0 : name.hashCode())
                + (surname == null ? 0 : surname.hashCode())
                + (role == null ? 0 : role.hashCode())
                + (account == null ? 0 : account.hashCode());
    }
}
