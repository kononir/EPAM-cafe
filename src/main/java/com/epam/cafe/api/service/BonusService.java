package com.epam.cafe.api.service;

import com.epam.cafe.entitie.Bonus;
import com.epam.cafe.service.exception.ServiceException;

import java.util.List;

public interface BonusService {
    List<Bonus> getClientBonuses(int clientID) throws ServiceException;
}
