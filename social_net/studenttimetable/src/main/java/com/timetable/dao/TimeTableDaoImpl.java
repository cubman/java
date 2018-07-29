package com.timetable.dao;

import com.timetable.entity.Lesson;
import com.timetable.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeTableDaoImpl implements TimetableDao {
    Connection connection;

    public TimeTableDaoImpl(String dataBaseUrl) throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(dataBaseUrl, "sa", "");
    }

    @Override
    public List<Lesson> getLessonsByStudent(int studentId) {
        List<Lesson> result = new ArrayList<>();
        try(
                PreparedStatement getLessons = connection.prepareStatement("SELECT l.* FROM STUDENTS_VISIT sv JOIN LESSONS l on sv.LESSON_ID = l.ID WHERE sv.SUDENT_ID = " + studentId);
                ResultSet resultSet = getLessons.executeQuery();
        )
        {
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setDate(resultSet.getDate("date"));

                result.add(lesson);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Lesson> getLessonsByStudent(Student student) {
        return getLessonsByStudent(student.getId());
    }

    @Override
    public boolean insert(int studentId, int lessonId) {
        boolean result = false;

        try(
                PreparedStatement ps = insertInformation(studentId, lessonId);
        ) {
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private PreparedStatement insertInformation(int studentId, int lessonId) throws SQLException {
        String sql = "INSERT INTO STUDENTS_VISIT (SUDENT_ID, LESSON_ID) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, studentId);
        ps.setInt(2, lessonId);
        return ps;
    }

    @Override
    public boolean insert(Student student, Lesson lesson) {
        return insert(student.getId(), lesson.getId());
    }
}
