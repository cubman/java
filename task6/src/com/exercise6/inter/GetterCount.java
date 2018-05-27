package com.exercise6.inter;

public interface GetterCount {
    /**
     * Возвращает колличество геттеров в переданном класс
     * @param clazz класс в котором необходимо посчитать геттеры
     * @return возвращает количество найденных геттеров
     */
    int calcGetterCount(Class<?> clazz);

    /**
     *
     * @return просто какую-то строку
     */
    String fakeMethod();
}

