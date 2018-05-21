package com.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<? extends T> limit(List<? extends T> source, int size) {
        if (size > source.size())
            size = source.size();

        return source.subList(0, size);
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? extends T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T element : c1)
            if (c2.contains(element))
                return true;

        return false;
    }

    public static <T extends Comparable<T>> List<? super T> range(List<? extends T> list, T min, T max) {
        List<? super T> result = new ArrayList<>();

        for (T element : list)
            if (element.compareTo(max) > 0 && element.compareTo(min) < 0)
                result.add(element);

        return result;
    }

    public static <T> List<? super T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<? super T> result = new ArrayList<>();

        for (T element : list)
            if (comparator.compare(element, max) > 0 && comparator.compare(element, min) < 0)
                result.add(element);

        return result;
    }

}
