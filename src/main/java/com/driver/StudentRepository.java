package com.driver;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Component
public class StudentRepository {

    Map<String, Student> studentDB = new HashMap<>();
    Map<String, Teacher> teacherDB = new HashMap<>();
    Map<String, List<String>> studentTeacherPair = new HashMap<>();
    List<String> listOfStudentOfTeacher = new ArrayList<>();

    /* <--------- Get Methods -------------->*/
    public Student getStudentByName(String name) {
        return studentDB.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDB.get(name);
    }

    public List<String> getStudentsByTeacherName(String name) {
        return studentTeacherPair.get(name);
    }

    public List<String> getAllStudents() {

        List<String> listOfStudentsName = new ArrayList<>();

        for(String studentName : studentDB.keySet())
            listOfStudentsName.add(studentName);

        return listOfStudentsName;
    }

    /* <--------- Post Methods -------------->*/

    public void addStudent(Student student) {
        studentDB.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDB.put(teacher.getName(), teacher);
    }

    /* <--------- Put Methods -------------->*/

    public void addStudentTeacherPair(String student, String teacher) {

        if(studentTeacherPair.containsKey(teacher)) {
            listOfStudentOfTeacher = studentTeacherPair.get(teacher);
        }
        listOfStudentOfTeacher.add(student);
        studentTeacherPair.put(teacher, listOfStudentOfTeacher);
    }

    /* <--------- Delete Methods -------------->*/

    public void deleteTeacherByName(String teacher) {
        teacherDB.remove(teacher);
        studentTeacherPair.remove(teacher);
    }

    public void deleteAllTeachers() {
        teacherDB.clear();
        studentTeacherPair.clear();
    }

}
