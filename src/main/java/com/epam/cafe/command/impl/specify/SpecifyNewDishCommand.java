package com.epam.cafe.command.impl.specify;

import com.epam.cafe.api.Command;
import com.epam.cafe.command.impl.AbstractCommand;

public class SpecifyNewDishCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/dish_adding_form.jsp";

    @Override
    public String execute() {
        return PAGE;
    }
}
