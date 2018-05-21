package com.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        List<Number> list = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        List<Double> doubles = new ArrayList<>();

        CollectionUtils.add(doubles, new Double(4));

        for(Double d : doubles)
            System.out.println(d);

        CollectionUtils.addAll(doubles, list);

        for(Number n : list)
            System.out.println(n);

        CollectionUtils.containsAll(integers, list);
        CollectionUtils.containsAny(doubles, list);
        CollectionUtils.limit(doubles, 5);
        CollectionUtils.range(integers, 3, 5);
        CollectionUtils.removeAll(list, doubles);
        CollectionUtils.range(doubles, 4, 6, new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                return o1.intValue() -  o2.intValue();
            }
        });
    }
}
