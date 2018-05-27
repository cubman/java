package com.exercise6;

import com.exercise6.inter.GetterCount;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InvocationHandlerCollector implements InvocationHandler {

    private GetterCount getterCount;
    private Map<Class<?>, Integer> map = new HashMap<>();

    InvocationHandlerCollector(GetterCount getterCount) {
        this.getterCount = getterCount;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!method.getName().equals("calcGetterCount") || args.length != 1)
            return method.invoke(getterCount, args);

        Class<?> className = (Class<?>) args[0];

        if(map.containsKey(className)) {
            System.out.println(String.format("=== %s в кэше ===", className.toString()));
            return map.get(className);
        }

        int count = (int) method.invoke(getterCount, args);
        map.put(className, count);

        return count;
    }
}
