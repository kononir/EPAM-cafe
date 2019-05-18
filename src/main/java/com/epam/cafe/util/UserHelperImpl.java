package com.epam.cafe.util;

import com.epam.cafe.api.util.UserHelper;
import com.epam.cafe.entitie.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserHelperImpl implements UserHelper {

    @Override
    public User findUserByID(List usersAsAttributes, Integer userID) {
        List<User> users = new ArrayList<>();
        for (Object attribute : usersAsAttributes) {
            users.add((User) attribute);
        }

        List<User> foundUsers = users.stream()
                .filter(client -> userID.equals(client.getID()))
                .collect(Collectors.toList());

        return foundUsers.get(0);
    }
}
