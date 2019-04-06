package com.epam.web.epam.cafe;

import com.epam.web.epam.cafe.command.Command;
import com.epam.web.epam.cafe.command.exceptions.CommandExecutingException;
import com.epam.web.epam.cafe.command.factory.CommandFactory;
import com.epam.web.epam.cafe.command.exceptions.CommandCreatingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontServlet extends HttpServlet {

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
        } catch (CommandCreatingException | CommandExecutingException e) {
            req.setAttribute("error", e.getMessage());
            page = "/WEB-INF/view/error.jsp";
        }

        dispatch(req, resp, page);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
        requestDispatcher.forward(req, resp);
    }
}
