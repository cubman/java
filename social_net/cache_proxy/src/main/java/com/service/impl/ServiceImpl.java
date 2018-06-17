package com.service.impl;

import com.entity.Person;
import com.service.annotation.Cache;
import com.sevice.inter.IService;
import org.mockito.cglib.proxy.InvocationHandler;
import org.omg.CORBA.ParameterModeHolder;

import javax.sql.rowset.Predicate;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.service.annotation.CacheType.FILE;

public class ServiceImpl implements IService {

    public static Map<Integer, Person> data = new HashMap<>();


    @Override
    //@Cache(cacheType = FILE, fileNamePrefix = "data", identityBy = {String.class, double.class})
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
