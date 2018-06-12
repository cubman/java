package com.count_strings;


import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class Solution {

    public static void task2010() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(scanner.nextInt() + scanner.nextInt());
    }

    public static void task2020() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int current = scanner.nextInt(), current_max = 1;
        int max = 0, max_number = 0;

        for (int i = 0; i < n - 1; ++i) {
            int wasRead = scanner.nextInt();

            if (current == wasRead)
                ++current_max;
            else {
                if (current_max > max_number) {
                    max = current;
                    max_number = current_max;
                }

                current = wasRead;
                current_max = 1;
            }
        }

        if (max_number < current_max) {
            max = current;
            max_number = current_max;
        }

        System.out.println(String.format("%d %d", max, max_number));

    }

    public static void task2024() {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = new LinkedList<>();

        int n = scanner.nextInt();
        for (int i = 0; i < n; ++i)
            list.add(scanner.nextInt());

        int result = 0;

        for (int i = 0; i < n / 2; ++i)
            result += list.get(i).equals(list.get(n - 1 - i)) ? 0 : 1;

        System.out.println(result);
    }

    public static void task2024_2() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] list = Arrays.stream(new int[n])
                .map(operand -> scanner.nextInt())
                .toArray();

        int result = 0;

        for (int i = 0; i < n / 2; ++i)
            result += list[i] == list[n - 1 - i] ? 0 : 1;

        System.out.println(result);
    }

    public static void task2027() {
        Scanner scanner = new Scanner(System.in);

        List<Integer> list = new LinkedList<>();

        int n = scanner.nextInt();

        for (int i = 0; i < n; ++i)
            list.add(scanner.nextInt());

        int l1 = scanner.nextInt() - 1, r1 = scanner.nextInt() - 1, l2 = scanner.nextInt() - 1, r2 = scanner.nextInt() - 1;
        for (int i = 0; i <= (r1 - l1) / 2; ++i) {
            Integer t = list.get(r1 - i);
            list.set(r1 - i, list.get(l1 + i));
            list.set(l1 + i, t);
        }

        for (int i = 0; i <= (r2 - l2) / 2; ++i) {
            Integer t = list.get(r2 - i);
            list.set(r2 - i, list.get(l2 + i));
            list.set(l2 + i, t);
        }

        list
                .stream()
                .forEach(integer -> System.out.print(String.format("%d ", integer)));

    }

    public static void task2036() {
        Scanner scanner = new Scanner(System.in);

        Arrays
                .stream(new String[scanner.nextInt()])
                .map(s -> s = scanner.nextLine())
                .filter(s -> s.split("[eyuioa]{3}").length == 1)
                .forEach(System.out::println);
    }

    public static void task2038() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Arrays
                .stream(scanner.nextLine().split("[^a-zA-Z]+"))
                .map(s -> s.length())
                .max(Comparator.naturalOrder())
                .get());
    }

    public static void task2039() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(scanner.nextLine().split("[^a-zA-Z]+").length);
    }

    public static void main(String[] args) {
        task2036();
    }
}
