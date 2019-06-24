package com.epam.cafe.command.impl.delete;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.BonusService;
import com.epam.cafe.command.impl.AbstractCommand;
import com.epam.cafe.entitie.Bonus;
import com.epam.cafe.service.impl.BonusServiceImpl;
import com.epam.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteBonusCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/bonuses_table_admin.jsp";

    private HttpServletRequest request;

    public DeleteBonusCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        Bonus bonus = findBonus(request);

        BonusService service = new BonusServiceImpl();
        service.deleteBonus(bonus);

        removeBonusFromRequest(request, bonus);

        return PAGE;
    }
}
