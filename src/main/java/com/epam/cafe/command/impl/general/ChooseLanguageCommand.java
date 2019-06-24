package com.epam.cafe.command.impl.general;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.impl.AbstractCommand;
import com.epam.cafe.page.Language;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.entitie.user.UserRole;
import com.epam.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChooseLanguageCommand extends AbstractCommand implements Command {
    private HttpServletRequest request;

    public ChooseLanguageCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        String langStr = request.getParameter("language");
        Language language = Language.valueOf(langStr.toUpperCase());

        HttpSession session = request.getSession();
        session.setAttribute("locale", language.getLocale());

        User user = (User) session.getAttribute("user");
        UserRole role = user.getRole();

        return findPageByRole(role);
    }
}
