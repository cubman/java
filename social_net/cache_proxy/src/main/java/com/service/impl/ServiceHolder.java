package com.service.impl;

import com.entity.Person;
import com.serialise.SerializeUtil;
import com.service.annotation.Cache;
import com.sevice.inter.IService;

import javax.xml.ws.Service;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.service.annotation.CacheType.FILE;
import static java.util.stream.Collectors.toList;

public class ServiceHolder implements InvocationHandler {

    private IService service;

    public ServiceHolder(IService service) {
        this.service = service;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Cache.class) && method.getAnnotation(Cache.class).cacheType().equals(FILE))
        {
            String pref = method.getAnnotation(Cache.class).fileNamePrefix();

            switch (method.getName()) {
                case "save": {
                    if (args.length != 1)
                        throw new IllegalArgumentException("args length must be 1");

                    if (!args[0].getClass().equals(Person.class))
                        throw new IllegalArgumentException("args is not Person");

                    Person person = (Person) args[0];

                    SerializeUtil.serializeObject(person, pref + person.getId());
                    break;
                }
                case "read": {
                  return Files.list(Paths.get(System.getProperty("user.dir")))
                          .map(path -> path.getFileName())
                          .filter(path -> path.toString().startsWith(pref))
                            .map(path -> {
                                try {
                                    return SerializeUtil.deSerializeObject(path.toString());
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            })
                            .collect(toList());
                }
                case "readById": {
                    if (args.length != 1)
                        throw new IllegalArgumentException("args length must be 1");

                    if (!args[0].getClass().equals(Integer.class))
                        throw new IllegalArgumentException("args is not int");

                    int i = (int)args[0];

                    File file = new File(String.format("%s\\%s%d", System.getProperty("user.dir"), pref, i));

                    return SerializeUtil.deSerializeObject(file.getAbsolutePath());

                }
                default: {
                    throw new IndexOutOfBoundsException(method.getName() + " - was not handled");
                }
            }

            return  null;
        }
        else
            return method.invoke(service, args);
    }
}
