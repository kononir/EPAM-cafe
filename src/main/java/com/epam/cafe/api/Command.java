package com.epam.cafe.api;

import com.epam.cafe.service.exception.ServiceException;

public interface Command {
    String execute() throws ServiceException;
}
