package com.epam.cafe.api.service;

import com.epam.cafe.entitie.PaymentMethod;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.service.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public interface OrderService {
    void orderSelected(Map<Integer, Integer> chosenDishes, LocalDateTime dateTime, User currentUser, BigDecimal resultCost, PaymentMethod paymentMethod) throws ServiceException;
}
