package com;

import com.person.Person;
import com.person.Student;
import com.stream.Streams;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Student> someCollection = new LinkedList<>();

        someCollection.add(new Student("Иван", 21, 1));
        someCollection.add(new Student("Петр", 22, 2));
        someCollection.add(new Student("Виталий", 31, 3));
        someCollection.add(new Student("Сергей", 19, 4));

        Map<String, Integer> m = Streams.of(someCollection)
                .filter(p -> p.getAge() > 20)
                .transform( p -> new Student(p.getName(), p.getAge() + 30, p.getCourse()))
                                     .toMap(Person::getName, Person::getAge);

        m.forEach((s, integer) -> System.out.println(String.format("%s - %d", s, integer)));
    }
}
