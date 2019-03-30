package com.epam.web.epam.cafe.logic;

import com.epam.web.epam.cafe.entitie.user.User;
import com.epam.web.epam.cafe.entitie.user.UserRole;
import com.epam.web.epam.cafe.repository.Repository;
import com.epam.web.epam.cafe.repository.impl.UserRepository;
import com.epam.web.epam.cafe.repository.specification.impl.UserByLoginAndPasswordSpecification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserService {
    private Repository<User> userRepository = new UserRepository();
    private HttpServletRequest request;

    private static final int FIRST = 0;

    public UserService(HttpServletRequest request) {
        this.request = request;
    }

    public String authorize() {
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute("role");

        if (role == null) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            List<User> users = userRepository.query(new UserByLoginAndPasswordSpecification(login, password));

            if (!users.isEmpty()) {
                User user = users.get(FIRST);
                role = user.getRole();

                session.setAttribute("role", role);
            } else {
                role = UserRole.ANONYMOUS;
            }
        }

        return findPageByRole(role);
    }

    private String findPageByRole(UserRole userRole) {
        String page;

        switch (userRole) {
            case CLIENT:
                page = "/WEB-INF/view/user.jsp";
                break;
            case ADMINISTRATOR:
                page = "/WEB-INF/view/admin.jsp";
                break;
            case ANONYMOUS:
                page = "/WEB-INF/view/authorization.jsp";
                break;
            default:
                throw new EnumConstantNotPresentException(UserRole.class, userRole.name());
        }

        return page;
    }
}
