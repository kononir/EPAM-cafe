package com.epam.cafe.api;

import com.epam.cafe.service.ServiceException;

public interface Command {
    String execute() throws ServiceException;
}
