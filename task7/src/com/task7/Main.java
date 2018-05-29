package com.task7;

import com.task7.loader.ApiClassloader;
import com.task7.loader.AppClassloader;
import com.task7.loader.ImplClassLoader;
import com.task7.loader.MyAppClassLoader;
import com.task7.task.api.Calculator;
import com.task7.task.app.App;
import com.task7.task.app.MyApp;
import com.task7.task.impl.CalculatorImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        try {
            CalculatorImpl calculatorImpl = (CalculatorImpl)new ImplClassLoader().
                    loadClass("com.task7.task.impl.CalculatorImpl").
                    newInstance();

            App app = (App)new AppClassloader().
                    loadClass("com.task7.task.app.App").
                    newInstance();

//            CalculatorImpl calculatorImplError = (CalculatorImpl)new AppClassloader().
//                    loadClass("com.task7.task.impl.CalculatorImpl").
//                    newInstance();
//            ошибка(ненахождение в указанном пакете загрузки)

            MyApp my = (MyApp)new AppClassloader().
                    loadClass("com.task7.task.app.MyApp").
                    newInstance();


            Constructor constructor = new MyAppClassLoader().
                    loadClass("com.task7.task.app.MyApp").
                    getConstructor(calculatorImpl.getClass());

            MyApp myApp = (MyApp)constructor.newInstance(calculatorImpl);




        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
