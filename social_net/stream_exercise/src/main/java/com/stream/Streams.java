package com.stream;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {

    List<T> list;

    private Streams(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    public static<T> Streams<T> of(List<T> list) {
        return new Streams<T>(list);
    }

    public Streams<T> filter(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for (int i = 0; i < list.size(); ++i) {
            if(predicate.test(list.get(i)))
                result.add(list.get(i));
        }

        list = result;

        return this;
    }

    public Streams<T> transform(Function<? super T, T> function) {
        for (int i = 0; i < list.size(); ++i) {
            list.set(i, function.apply(list.get(i)));
        }

        return this;
    }

    public<K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        Map<K, V> map = new HashMap<>();

        for (int i = 0; i < list.size(); ++i) {
            map.put(keyFunction.apply(list.get(i)), valueFunction.apply(list.get(i)));
        }

        return map;
    }


}
