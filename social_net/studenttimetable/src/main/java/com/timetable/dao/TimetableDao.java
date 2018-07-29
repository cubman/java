package com.timetable.dao;

import com.timetable.entity.Lesson;
import com.timetable.entity.Student;

import java.util.List;

public interface TimetableDao {
    List<Lesson> getLessonsByStudent(int studentId);
    List<Lesson> getLessonsByStudent(Student student);

    boolean insert(int studentId, int lessonId);
    boolean insert(Student student, Lesson lesson);
}
