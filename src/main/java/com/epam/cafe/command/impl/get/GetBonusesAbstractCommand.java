package com.epam.cafe.command.impl.get;

import com.epam.cafe.api.service.BonusService;
import com.epam.cafe.command.impl.AbstractCommand;
import com.epam.cafe.entitie.Bonus;
import com.epam.cafe.service.impl.BonusServiceImpl;
import com.epam.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class GetBonusesAbstractCommand extends AbstractCommand {
    private HttpServletRequest request;

    public GetBonusesAbstractCommand(HttpServletRequest request) {
        this.request = request;
    }

    protected List<Bonus> getBonuses(int clientID) throws ServiceException {
        HttpSession session = request.getSession();
        BonusService service = new BonusServiceImpl();

        int recordsCount = findRecordsCount(request);
        int pageNumber = findPageNumber(request);

        List<Bonus> bonuses;
        if (pageNumber == 1) {
            bonuses = service.getClientBonuses(clientID);
            int bonusesCount = bonuses.size();
            findPageCount(session, bonusesCount, recordsCount);

            if (bonusesCount > recordsCount) {
                bonuses = bonuses.subList(0, recordsCount);
            }
        } else {
            int skippingPagesNumber = pageNumber - 1;
            bonuses = service.getClientBonuses(clientID, skippingPagesNumber, recordsCount);
        }

        return bonuses;
    }
}
