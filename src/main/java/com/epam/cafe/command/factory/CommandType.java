package com.epam.cafe.command.factory;

public enum CommandType {
    AUTHORIZE,
    LOGOUT,

    GET_CLIENTS,
    GET_BONUSES,

    MANAGE_CLIENT_INFORMATION,
    SAVE_CLIENT_CHANGES,

    GET_FULL_MENU,

    GET_CLIENT_MENU,
    SAVE_DISH_ORDER,

    GET_CLIENT_ORDERS,
    GET_CLIENT_BONUSES,
    SHOW_BASKET
}
