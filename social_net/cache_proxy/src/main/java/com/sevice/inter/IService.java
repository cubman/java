package com.sevice.inter;

import com.entity.Person;
import com.service.annotation.Cache;

import java.util.List;

import static com.service.annotation.CacheType.FILE;
import static com.service.annotation.CacheType.IN_MEMORY;

public interface IService {
    @Cache(cacheType = FILE)
    void save(Person person);

    @Cache(cacheType = FILE)
    List<Person> read();

    @Cache(cacheType = FILE)
    Person readById(int id);
}
