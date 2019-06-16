<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 12.06.2019
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Basket</title>
    <link href="view/style/main.css" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1>Basket</h1>
            <div class="table-actions">
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Cost</th>
                        <th>Servings number</th>
                        <th>Total cost</th>
                        <th>Remove from order</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="dishes" type="java.util.List<com.epam.cafe.entitie.dish.Dish>"--%>
                    <c:forEach items="${dishes}" var="dish">
                        <tr>
                            <td>${dish.name}</td>

                            <c:set var="dishCost" value="${dish.cost}" scope="page"/>
                            <td>${dishCost}$</td>

                            <c:set var="dishID" value="${dish.ID}" scope="page"/>
                                <%--@elvariable id="order" type="java.util.Map<Integer, Integer>"--%>
                            <c:set var="servingsNumber" value="${order.get(dishID)}" scope="page"/>
                            <td>${servingsNumber}</td>

                                <%--@elvariable id="generalCosts" type="java.util.Map<Integer, java.math.BigDecimal>"--%>
                            <td>${generalCosts.get(dishID)}$</td>

                            <td>
                                <form action="command" method="post">
                                    <input type="hidden" name="command" value="remove_dish_order">
                                    <input type="hidden" name="selectedDishID" value="${dishID}">
                                    <button type="submit">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="4">
                            <%--@elvariable id="resultCost" type="java.math.BigDecimal"--%>
                            Result - ${resultCost}$
                        </td>
                        <td>
                            <form action="command" method="post">
                                <input type="hidden" name="command" value="remove_all_dish_order">
                                <button type="submit">All</button>
                            </form>
                        </td>
                    </tr>
                    </tfoot>
                </table>

                <form action="command" method="post">
                    <label for="date">Choose date</label><br>
                    <input id="date" type="date" name="date"><br>
                    <label for="time">Choose time</label><br>
                    <input id="time" type="time" name="time"><br>
                    <label for="payment-method">Choose payment method</label><br>
                    <select id="payment-method" name="paymentMethod">
                        <option value="cash">Cash</option>
                        <option value="credit_card">Credit card</option>
                    </select><br>
                    <input type="hidden" name="command" value="order">
                    <button type="submit">Order</button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
