package com.epam.cafe;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.factory.CommandFactory;
import com.epam.cafe.connection.ConnectionPool;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.entitie.user.UserRole;
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
    public void init() {
        ConnectionPool.getInstance();
    }

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
            UserRole role = user.getRole();
            switch (role) {
                case CLIENT:
                    page = "/view/page/client/client_error.jsp";
                    break;
                case ADMINISTRATOR:
                    page = "/view/page/administrator/admin_error.jsp";
                    break;
                default:
                    page = "/view/page/general/authorization.jsp";
            }
        }

        dispatch(req, resp, page);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
        requestDispatcher.forward(req, resp);

        // Добавить новое скрытое поле в формы для выбора между forward и redirect
        /*
        resp.sendRedirect(req.getContextPath() + page);
        */
    }
}
