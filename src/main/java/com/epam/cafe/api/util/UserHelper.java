package com.epam.cafe.api.util;

import com.epam.cafe.entitie.user.User;

import java.util.List;

public interface UserHelper {
    User findUserByID(List usersAsAttributes, Integer userID);
}
