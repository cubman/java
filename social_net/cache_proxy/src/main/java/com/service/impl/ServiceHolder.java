package com.service.impl;

import com.service.annotation.Cache;
import com.sevice.inter.IService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceHolder implements InvocationHandler {

    private IService service;

    ServiceHolder(IService service) {
        this.service = service;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("run"))
        {
            if(method.isAnnotationPresent(Cache.class))
            {

            }

            return  null;
        }
        else
            return method.invoke(service, args);
    }
}
