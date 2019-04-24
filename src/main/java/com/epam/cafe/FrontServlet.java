package com.epam.cafe;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.factory.CommandFactory;
import com.epam.cafe.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        try {
            String commandName = req.getParameter("command");
            CommandFactory commandFactory = new CommandFactory(req);
            Command command = commandFactory.create(commandName);

            page = command.execute();
        } catch (Exception e) {
            req.setAttribute("exception", e);
            page = "/view/error.jsp";
        }

        dispatch(req, resp, page);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
        requestDispatcher.forward(req, resp);
    }
}
