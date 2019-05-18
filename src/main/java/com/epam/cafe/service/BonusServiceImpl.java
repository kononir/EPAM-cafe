package com.epam.cafe.service;

import com.epam.cafe.api.repository.Repository;
import com.epam.cafe.api.service.BonusService;
import com.epam.cafe.entitie.Bonus;
import com.epam.cafe.repository.exception.RepositoryException;
import com.epam.cafe.repository.factory.RepositoryFactory;
import com.epam.cafe.repository.specification.bonus.BonusByUserIDSpecification;
import com.epam.cafe.service.exception.ServiceException;

import java.util.List;

public class BonusServiceImpl implements BonusService {

    @Override
    public List<Bonus> getClientBonuses(int clientID) throws ServiceException {
        List<Bonus> bonuses;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<Bonus> bonusRepository = factory.bonusRepository();
            bonuses = bonusRepository.query(new BonusByUserIDSpecification(clientID));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting clients bonuses error.", e);
        }

        return bonuses;
    }
}
