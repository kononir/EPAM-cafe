<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="idDishMap" type="java.util.Map<java.lang.Integer, com.epam.cafe.entitie.Dish>"--%>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.06.2019
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Previous orders</title>
    <link href="view/style/main.css" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1>Previous orders</h1>

            <%--@elvariable id="orders" type="java.util.List<com.epam.cafe.entitie.order.Order>"--%>
            <c:forEach var="order" items="${orders}">
                <div class="entity">
                    <h3>Date and time - ${order.receiptTime}</h3>

                    <p>Chosen dishes:</p>
                    <ul>
                        <c:forEach var="orderDish" items="${order.chosenDishes}">
                            <li>${idDishMap.get(orderDish.key).name} (x${orderDish.value})</li>
                        </c:forEach>
                    </ul>

                    <p>Result - ${order.resultCost}$</p>

                    <p>Payment method -
                        <c:choose>
                            <c:when test="${order.paymentMethod == 'CREDIT_CARD'}">
                                credit card
                            </c:when>
                            <c:when test="${order.paymentMethod == 'CASH'}">
                                cash
                            </c:when>
                        </c:choose>
                    </p>

                    <form id="rating-form" method="post">
                        <input type="hidden" name="command" value="rate_order">
                        <input type="hidden" name="orderID" value="${order.ID}">

                        <label>Rating</label>
                        <div class="rating_block">
                            <c:choose>
                                <c:when test="${order.score == 5}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="5"
                                           id="rating_5" type="radio" checked/>
                                    <label for="rating_5" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="5"
                                           id="rating_5" type="radio"/>
                                    <label for="rating_5" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.score == 4}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="4"
                                           id="rating_4" type="radio" checked/>
                                    <label for="rating_4" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="4"
                                           id="rating_4" type="radio"/>
                                    <label for="rating_4" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.score == 3}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="3"
                                           id="rating_3" type="radio" checked/>
                                    <label for="rating_3" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="3"
                                           id="rating_3" type="radio"/>
                                    <label for="rating_3" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.score == 2}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="2"
                                           id="rating_2" type="radio" checked/>
                                    <label for="rating_2" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="2"
                                           id="rating_2" type="radio"/>
                                    <label for="rating_2" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${order.score == 1}">
                                    <input onclick="submitForm('rating-form')" name="rating" value="1"
                                           id="rating_1" type="radio" checked/>
                                    <label for="rating_1" class="label_rating_changeable"></label>
                                </c:when>
                                <c:otherwise>
                                    <input onclick="submitForm('rating-form')" name="rating" value="1"
                                           id="rating_1" type="radio"/>
                                    <label for="rating_1" class="label_rating_changeable"></label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form>

                    <form action="command" method="post">
                        <label for="order-comment">Comment</label>
                        <c:choose>
                            <c:when test="${order.comment != null}">
                                <c:set value="${order.comment}" var="orderComment" scope="page"/>
                            </c:when>
                            <c:otherwise>
                                <c:set value="" var="orderComment" scope="page"/>
                            </c:otherwise>
                        </c:choose>
                        <textarea id="order-comment" name="comment" maxlength="1000"
                                  required>${orderComment}</textarea>

                        <input type="hidden" name="orderID" value="${order.ID}">
                        <input type="hidden" name="command" value="leave_comment">
                        <button type="submit">Leave comment</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
<script src="js/main.js"></script>
</body>
</html>
