package com.epam.cafe.command.impl.delete;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.DishService;
import com.epam.cafe.command.impl.AbstractCommand;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.service.impl.DishServiceImpl;
import com.epam.cafe.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteDishCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/all_dishes.jsp";

    private HttpServletRequest request;

    public DeleteDishCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        Dish dish = findDish(request);

        DishService service = new DishServiceImpl();
        service.deleteDish(dish);

        removeDishFromRequest(request, dish);

        return PAGE;
    }
}
