package com.java.school;

public class Main {

    public static void main(String[] args) {
        Person person1 = new Person(false, "Галя");
        Person person2 = new Person(true, "Вася");
        Person person3 = new Person(false, "Света");
        Person person4 = new Person(true, "Петя");

        person1.marry(person2);
         person3.marry(person4);
        person1.marry(person4);
        person1.marry(person4);

        System.out.println(person1.toString());
        System.out.println(person2.toString());
        System.out.println(person3.toString());
        System.out.println(person4.toString());

    }
}
