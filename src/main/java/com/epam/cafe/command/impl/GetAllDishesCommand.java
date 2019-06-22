package com.epam.cafe.command.impl;

import com.epam.cafe.api.Command;
import com.epam.cafe.api.service.DishService;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.service.DishServiceImpl;
import com.epam.cafe.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAllDishesCommand extends AbstractCommand implements Command {
    private static final String PAGE = "/view/page/administrator/all_dishes.jsp";

    private HttpServletRequest request;

    public GetAllDishesCommand(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() throws ServiceException {
        DishService service = new DishServiceImpl();
        HttpSession session = request.getSession();

        int recordsCount = findRecordsCount(request);
        int pageNumber = findPageNumber(request);

        List<Dish> allDishes;
        if (pageNumber == 1) {
            allDishes = service.getAllDishes();
            int dishesCount = allDishes.size();
            findPageCount(session, dishesCount, recordsCount);

            if (dishesCount > recordsCount) {
                allDishes = allDishes.subList(0, recordsCount);
            }
        } else {
            int skippingPagesNumber = pageNumber - 1;
            allDishes = service.getAllDishes(skippingPagesNumber, recordsCount);
        }

        session.setAttribute("allDishes", allDishes);

        return PAGE;
    }
}
