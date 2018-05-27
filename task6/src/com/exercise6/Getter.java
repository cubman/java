package com.exercise6;

public class Getter {

    private String get;
    protected int getI;
    public double smth;

    @Skip
    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public int getGetI() {
        return getI;
    }

    public void setGetI(int getI) {
        this.getI = getI;
    }

    public double getSmth() {
        return smth;
    }

    @Skip
    public void setSmth(double smth) {
        this.smth = smth;
    }

    public double getSum() {
        return smth + getI;
    }
}
