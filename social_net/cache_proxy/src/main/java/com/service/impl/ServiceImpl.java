package com.service.impl;

import com.sevice.inter.IService;
import org.mockito.cglib.proxy.InvocationHandler;
import org.omg.CORBA.ParameterModeHolder;

import javax.sql.rowset.Predicate;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceImpl implements IService {

    public static Map<String, Double> data;

    @Override
    public List<String> run(String item, double value) {
        return null;
    }

    @Override
    public List<String> work(final String item) {
        return new ArrayList<>((data.keySet()))
                .stream()
                .filter(p -> !p.equals(item))
                .collect(Collectors.toList());
    }
}
