package com.epam.web.epam.cafe.entitie.user;

import com.epam.web.epam.cafe.entitie.Account;

public class User {
    private int ID;
    private String login;
    private String password;
    private String name;
    private String surname;
    private UserRole role;

    private Account account;

    public User(int ID, String login, String password, String name, String surname, UserRole role) {
        this.ID = ID;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(int ID, String login, String password, String name, String surname, UserRole role, Account account) {
        this.ID = ID;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
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
                role == user.role;
    }

    @Override
    public int hashCode() {
        return 31 * ID + (login == null ? 0 : login.hashCode())
                + (password == null ? 0 : password.hashCode())
                + (name == null ? 0 : name.hashCode())
                + (surname == null ? 0 : surname.hashCode())
                + (role == null ? 0 : role.hashCode());
    }
}
