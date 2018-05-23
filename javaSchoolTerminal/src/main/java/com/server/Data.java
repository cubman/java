package com.server;

import java.util.Date;

public class Data<T1, T2, T3, T4> {
    private T1 user;
    private T2 cardNum;
    private T3 password;
    private T4 money;


    public Data(T1 user, T2 cardNum, T3 password, T4 money) {
        this.user = user;
        this.cardNum = cardNum;
        this.password = password;
        this.money = money;
    }

    public T1 getUser() {
        return user;

    }

    public T2 getCardNum() {
        return cardNum;
    }

    public void setCardNum(T2 cardNum) {
        this.cardNum = cardNum;
    }

    public T3 getPassword() {
        return password;
    }

    public void setPassword(T3 password) {
        this.password = password;
    }

    public T4 getMoney() {
        return money;
    }

    public void setMoney(T4 money) {
        this.money = money;
    }

    public Data<T1, T2, T3, T4> clone() {
        return new Data<>(user, cardNum, password, money);
    }
}
