package com.epam.cafe.page;

public enum Language {
    ENGLISH("en_US"),
    RUSSIAN("ru_RU"),
    BELORUSSIAN("be_BY");

    private String locale;

    Language(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
