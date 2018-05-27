package com.exercise6.implementation;

import com.exercise6.Skip;
import com.exercise6.inter.GetterCount;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetterCountImpl implements GetterCount {

    Map<Class<?>, Integer> data = new HashMap<>();

    @Override
    public int calcGetterCount(Class<?> clazz) {

        if (clazz == null)
            return 0;

        int count = 0;

        List<String> allFields = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            String name = field.getName();
            allFields.add(name.substring(0, 1).toUpperCase() + name.substring(1));
        }

        for (Method m : clazz.getDeclaredMethods())
            if (m.getName().indexOf("get") == 0 && allFields.contains(m.getName().substring(3)) && !m.isAnnotationPresent(Skip.class))
                ++count;

        return count + calcGetterCount(clazz.getSuperclass());
    }

    @Override
    public String fakeMethod() {
        return "Какая-то строка";
    }
}
