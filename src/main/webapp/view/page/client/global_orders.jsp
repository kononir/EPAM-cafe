<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="idUserMap" type="java.util.Map<java.lang.Integer, com.epam.cafe.entitie.user.User>"--%>
<%--@elvariable id="idDishMap" type="java.util.Map<java.lang.Integer, com.epam.cafe.entitie.Dish>"--%>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.06.2019
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Global orders</title>
    <link href="view/style/main.css" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1>Global orders</h1>

            <%--@elvariable id="orders" type="java.util.List<com.epam.cafe.entitie.order.Order>"--%>
            <c:forEach var="order" items="${orders}">
                <div class="entity">
                    <h3>User - ${idUserMap.get(order.userID).login}</h3>

                    <h3>Date and time - ${order.receiptTime}</h3>

                    <p>Chosen dishes:</p>
                    <ul>
                        <c:forEach var="orderDish" items="${order.chosenDishes}">
                            <li>${idDishMap.get(orderDish.key).name} (x${orderDish.value})</li>
                        </c:forEach>
                    </ul>

                    <p>Result - ${order.resultCost}$</p>

                    <label>Rating</label>
                    <div class="rating_block">
                        <c:choose>
                            <c:when test="${order.score == 5}">
                                <input onclick="submitForm('rating-form')" name="rating" value="5"
                                       id="rating_5" type="radio" disabled checked/>
                                <label for="rating_5" class="label_rating_unchangeable"></label>
                            </c:when>
                            <c:otherwise>
                                <input onclick="submitForm('rating-form')" name="rating" value="5"
                                       id="rating_5" type="radio" disabled/>
                                <label for="rating_5" class="label_rating_unchangeable"></label>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${order.score == 4}">
                                <input onclick="submitForm('rating-form')" name="rating" value="4"
                                       id="rating_4" type="radio" disabled checked/>
                                <label for="rating_4" class="label_rating_unchangeable"></label>
                            </c:when>
                            <c:otherwise>
                                <input onclick="submitForm('rating-form')" name="rating" value="4"
                                       id="rating_4" type="radio" disabled/>
                                <label for="rating_4" class="label_rating_unchangeable"></label>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${order.score == 3}">
                                <input onclick="submitForm('rating-form')" name="rating" value="3"
                                       id="rating_3" type="radio" disabled checked/>
                                <label for="rating_3" class="label_rating_unchangeable"></label>
                            </c:when>
                            <c:otherwise>
                                <input onclick="submitForm('rating-form')" name="rating" value="3"
                                       id="rating_3" type="radio" disabled/>
                                <label for="rating_3" class="label_rating_unchangeable"></label>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${order.score == 2}">
                                <input onclick="submitForm('rating-form')" name="rating" value="2"
                                       id="rating_2" type="radio" disabled checked/>
                                <label for="rating_2" class="label_rating_unchangeable"></label>
                            </c:when>
                            <c:otherwise>
                                <input onclick="submitForm('rating-form')" name="rating" value="2"
                                       id="rating_2" type="radio" disabled/>
                                <label for="rating_2" class="label_rating_unchangeable"></label>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${order.score == 1}">
                                <input onclick="submitForm('rating-form')" name="rating" value="1"
                                       id="rating_1" type="radio" disabled checked/>
                                <label for="rating_1" class="label_rating_unchangeable"></label>
                            </c:when>
                            <c:otherwise>
                                <input onclick="submitForm('rating-form')" name="rating" value="1"
                                       id="rating_1" type="radio" disabled/>
                                <label for="rating_1" class="label_rating_unchangeable"></label>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <label for="order-comment">Comment</label>
                    <c:choose>
                        <c:when test="${order.comment != null}">
                            <c:set value="${order.comment}" var="orderComment" scope="page"/>
                        </c:when>
                        <c:otherwise>
                            <c:set value="" var="orderComment" scope="page"/>
                        </c:otherwise>
                    </c:choose>
                    <textarea id="order-comment" name="comment" readonly>${orderComment}</textarea>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
<script src="js/main.js"></script>
</body>
</html>
