package com.entity;

import com.serialise.SerializeUtil;
import com.service.annotation.Cache;

import java.io.Serializable;

import static com.service.annotation.CacheType.IN_MEMORY;


public class Person implements Serializable {
    private static int createdId;
    private int id;
    private String name;
    private int age;

    private Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        id = createdId++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("| %d - %s, %d |", id, name, age);
    }

    private Object writeReplace() {
        return new PersonProxy(this);
    }

    private static class PersonProxy implements Serializable {
        private String name;
        private int id;
        private int age;

        PersonProxy(Person p) {
            this.name = p.name;
            this.id = p.id;
            this.age = p.age;
        }

        private Object readResolve() {
            return new Person(name, age, id);
        }
    }
}
