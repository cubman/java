package com.task7.task.app;

import com.task7.task.impl.CalculatorImpl;

public class MyApp extends App {
    private CalculatorImpl calculator;

    public MyApp() {
        this.calculator = null;
    }

    public MyApp(CalculatorImpl calculator) {
        this.calculator = calculator;
    }
}
