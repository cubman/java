package com.task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    static class MySet {
        int minElement = Integer.MAX_VALUE;

        PriorityQueue<Integer> list = new PriorityQueue<>();

        public void add(Integer integer) {
         /*   if (integer < minElement)
                minElement = integer;*/

            list.add(integer);
        }

        public int removeMin() {

           /* if(!list.contains(minElement))
                findMin();*/
            return list.remove();
        }

      /*  private void findMin() {
            minElement = Integer.MAX_VALUE;

            for(int i = 0; i < list.size(); ++i)
                if(minElement > list.get(i))
                    minElement = list.get(i);
        }*/
    }

    public static String[] lineToWords(String textLine) {
        return textLine.toLowerCase().split("//s+");
    }

    public static List<String> maxWords(Map<String, Integer> dictionary) {
        List<String> result = new ArrayList<>();

        int max = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            Integer value = entry.getValue();

            if (max < value)
                max = value;
        }

        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (max == value)
                result.add(key);
        }

        return result;
    }

    public static void task2056() {
        Map<String, Integer> dictionary = new HashMap<>();

        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader("input.txt");
            br = new BufferedReader(fr);

            String sCurrentLine;

            Pattern pattern = Pattern.compile("(\\w+)");


            while ((sCurrentLine = br.readLine()) != null) {
               /* String[] words = lineToWords(sCurrentLine);
                for (int i = 0; i < words.length; ++i)
                    if (dictionary.containsKey(words[i]))
                        dictionary.put(words[i], dictionary.get(words[i]) + 1);
                    else
                        dictionary.put(words[i], 1);*/
                Matcher matcher = pattern.matcher(sCurrentLine);
                while (matcher.find()) {
                    String key = matcher.group().toLowerCase();
                        if (dictionary.containsKey(key))
                            dictionary.put(key, dictionary.get(key) + 1);
                        else
                            dictionary.put(key, 1);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }

        List<String> result = maxWords(dictionary);

        result.sort((o1, o2) -> o1.compareTo(o2));

        for (String aResult : result)
            System.out.println(aResult);
    }

    public static void task2057() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        MySet mySet = new MySet();

        for (int i = 0; i < n; ++i) {
            int k = scanner.nextInt();

            if (k == 1) {
                mySet.add(scanner.nextInt());
            } else {
                System.out.println(mySet.removeMin());
            }
        }
    }

    public static void main(String[] args) {
        task2056();
    }
}
