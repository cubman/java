package com.exercise6;

import com.exercise6.implementation.GetterCountImpl;
import com.exercise6.inter.GetterCount;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        GetterCount g = addInvocation(new GetterCountImpl());
        System.out.println(g.calcGetterCount(Getter.class));
        System.out.println(g.calcGetterCount(Vector.class));
        System.out.println(g.calcGetterCount(Vector.class));
        System.out.println(g.calcGetterCount(Array.class));
        System.out.println(g.calcGetterCount(Getter.class));
        System.out.println(g.calcGetterCount(com.exercise6.inter.GetterCount.class));
        System.out.println(g.calcGetterCount(com.exercise6.implementation.GetterCount.class));
        System.out.println(g.fakeMethod());
    }

    private static GetterCount addInvocation(GetterCount getterCount) {
        return (GetterCount)Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{GetterCount.class},
                new InvocationHandlerCollector(getterCount));
    }
}
