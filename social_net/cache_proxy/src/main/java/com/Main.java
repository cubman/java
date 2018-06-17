package com;

import com.entity.Person;
import com.service.impl.ServiceHolder;
import com.service.impl.ServiceImpl;
import com.sevice.inter.IService;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IService service = addInvocation(new ServiceImpl());

        List<Person> people = new ArrayList<>();
        people.add(new Person("Ivan", 11));
        people.add(new Person("Petr", 18));
        people.add(new Person("Victor", 98));

        people.stream().forEach(service::save);
        service.read().stream().forEach(System.out::print);

        System.out.println(service.readById(11));

    }
    private static IService addInvocation(IService service) {
        return (IService) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{IService.class},
                new ServiceHolder(service));
    }

}
