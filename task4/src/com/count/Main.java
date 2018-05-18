package com.count;

import com.count.impl.CountMapIml;
import com.count.interfaces.CountMap;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapIml<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        int count = map.getCount(5); // 2
        int count1 = map.getCount(6); // 1
        int count2 = map.getCount(10); // 3

        System.out.println(map.remove(10));
        // System.out.println(map.remove(3)); // Exception

        Map<Integer, Integer> m = new HashMap<>();
        map.toMap(m);

        System.out.println("keys value");
        for (Integer i : m.values()) {
            System.out.println(i);
        }
    }
}
