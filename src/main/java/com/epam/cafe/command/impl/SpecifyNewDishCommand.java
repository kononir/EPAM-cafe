package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;

public class SpecifyNewDishCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/dish_adding_form.jsp";

    @Override
    public String execute() {
        return PAGE;
    }
}
