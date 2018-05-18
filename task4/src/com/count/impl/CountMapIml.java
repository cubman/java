package com.count.impl;

import com.count.interfaces.CountMap;

import java.util.HashMap;
import java.util.Map;

public class CountMapIml<T> implements CountMap<T> {
    Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T o) {
        if(map.containsKey(o))
            map.put(o, map.get(o) + 1);
        else
            map.put(o, 1);
    }

    @Override
    public int getCount(T o) {
        if(map.containsKey(o))
           return map.get(o);

        throw new IndexOutOfBoundsException("map has no key " + o.toString());

    }

    @Override
    public int remove(T o) {
        if(map.containsKey(o))
            return map.remove(o);

        throw new IndexOutOfBoundsException("map has no key " + o.toString());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        map.putAll(source.toMap());
    }

    @Override
    public Map<T, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(map);
    }
}
