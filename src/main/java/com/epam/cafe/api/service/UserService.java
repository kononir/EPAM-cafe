package com.epam.cafe.api.service;

import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> authorize(String login, String password) throws ServiceException;

    List<User> getClients() throws ServiceException;

    void updateUser(User user) throws ServiceException;

    List<User> getUsersByIds(List<Integer> userIDs) throws ServiceException;

    List<User> getClients(int skippingPagesNumber, int recordsCount) throws ServiceException;
}
