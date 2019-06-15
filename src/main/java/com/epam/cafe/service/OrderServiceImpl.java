package com.epam.cafe.service;

import com.epam.cafe.api.repository.Repository;
import com.epam.cafe.api.service.OrderService;
import com.epam.cafe.entitie.Account;
import com.epam.cafe.entitie.order.Order;
import com.epam.cafe.entitie.PaymentMethod;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.repository.exception.RepositoryException;
import com.epam.cafe.repository.factory.RepositoryFactory;
import com.epam.cafe.repository.specification.account.AccountByIDSpecification;
import com.epam.cafe.service.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private static final int FIRST = 0;
    private static final PaymentMethod CREDIT_CARD = PaymentMethod.CREDIT_CARD;

    @Override
    public void orderSelected(Map<Integer, Integer> chosenDishes, LocalDateTime dateTime, User currentUser,
                              BigDecimal resultCost, PaymentMethod paymentMethod) throws ServiceException {
        try (RepositoryFactory factory = new RepositoryFactory()) {
            factory.startTransaction();

            withdrawMoney(factory, paymentMethod, currentUser, resultCost);
            saveOrder(factory, new Order(chosenDishes, dateTime, currentUser.getID()));

            factory.finishTransaction();
        } catch (RepositoryException e) {
            throw new ServiceException("Ordering selected dishes error.", e);
        }
    }

    private void withdrawMoney(RepositoryFactory factory, PaymentMethod paymentMethod, User currentUser,
                               BigDecimal resultCost) throws RepositoryException, ServiceException {
        if (CREDIT_CARD.equals(paymentMethod)) {
            Repository<Account> accountRepository = factory.accountRepository();
            List<Account> accounts = accountRepository.query(
                    new AccountByIDSpecification(currentUser.getAccountID())
            );

            Account account = accounts.get(FIRST);
            BigDecimal accountMoney = account.getMoney();

            boolean isEnoughMoney = accountMoney.compareTo(resultCost) >= 0;
            if (!isEnoughMoney) {
                throw new ServiceException("Ordering selected dishes error. There is no enough money at account.");
            }

            account.setMoney(accountMoney.subtract(resultCost));
            accountRepository.update(account);
        }
    }

    private void saveOrder(RepositoryFactory factory, Order order) throws RepositoryException {
        Repository<Order> orderRepository = factory.orderRepository();
        orderRepository.save(order);
    }
}
