package com.epam.cafe.api.service;

import com.epam.cafe.entitie.Bonus;
import com.epam.cafe.service.ServiceException;

import java.util.List;

public interface BonusService {
    List<Bonus> getClientBonuses(int clientID) throws ServiceException;

    void deleteBonus(Bonus bonus) throws ServiceException;

    void addBonus(Bonus bonus) throws ServiceException;

    Bonus getLastBonus() throws ServiceException;

    List<Bonus> getClientBonuses(int userID, int skippingPagesNumber, int recordsCount) throws ServiceException;
}
