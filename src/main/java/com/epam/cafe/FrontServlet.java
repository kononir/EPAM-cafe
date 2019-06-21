package com.epam.cafe;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.factory.CommandFactory;
import com.epam.cafe.connection.ConnectionPool;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.page.NavigationWay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class FrontServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public void destroy() {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            pool.closeAll();
        } catch (SQLException e) {
            LOGGER.error("Closing database connections exception", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String page;
        String navigationWayParam = req.getParameter("navigationWay");
        NavigationWay navigationWay = NavigationWay.valueOf(navigationWayParam.toUpperCase());
        try {
            String commandName = req.getParameter("command");
            CommandFactory commandFactory = new CommandFactory(req);
            Command command = commandFactory.create(commandName);

            page = command.execute();
        } catch (Exception e) {
            LOGGER.error("", e);
            req.setAttribute("exception", e);

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            switch (user.getRole()) {
                case CLIENT:
                    page = "/view/page/client/client_error.jsp";
                    break;
                case ADMINISTRATOR:
                    page = "/view/page/administrator/admin_error.jsp";
                    break;
                default:
                    page = "/view/page/general/authorization.jsp";
            }

            navigationWay = NavigationWay.FORWARD;
        }

        dispatch(req, resp, page, navigationWay);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page,
                          NavigationWay navigationWay) throws ServletException, IOException {
        switch (navigationWay) {
            case FORWARD:
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
                requestDispatcher.forward(req, resp);
                break;
            case REDIRECT:
                String url = req.getContextPath() + page;
                resp.sendRedirect(url);
                break;
        }
    }
}
