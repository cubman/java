package com.service.impl;

import com.entity.Person;
import com.sevice.inter.IService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.service.annotation.CacheType.FILE;

public class ServiceImpl implements IService {

    private static Map<Integer, Person> data = new HashMap<>();


    @Override
    public void save(Person person) {
        data.put(person.getId(), person);
    }

    @Override
    public List<Person> read() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Person readById(int id) {
        if(data.containsKey(id))
            return data.get(id);

        return null;
    }
}
