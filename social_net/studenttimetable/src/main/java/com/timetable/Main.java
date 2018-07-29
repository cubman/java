package com.timetable;

import com.timetable.dao.TimeTableDaoImpl;
import com.timetable.dao.TimetableDao;
import com.timetable.entity.Lesson;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            TimetableDao timetableDao = new TimeTableDaoImpl("jdbc:h2:D:/JavaSchool/social_net/studenttimetable/src/main/java/com/timetable/dao\\..\\..\\..\\..\\resources");

            timetableDao.getLessonsByStudent(2).stream().forEach(System.out::println);

            timetableDao.insert(2, 3);

            System.out.println("added (Петр Анатольевич -> Математика)");

            timetableDao.getLessonsByStudent(2).stream().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
