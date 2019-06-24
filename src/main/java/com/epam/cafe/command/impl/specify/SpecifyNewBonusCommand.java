package com.epam.cafe.command.impl.specify;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.impl.AbstractCommand;
import com.epam.cafe.service.ServiceException;

public class SpecifyNewBonusCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/bonus_adding_form.jsp";

    @Override
    public String execute() throws ServiceException {
        return PAGE;
    }
}
