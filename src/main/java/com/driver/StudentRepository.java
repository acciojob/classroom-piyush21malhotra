package com.driver;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Component
public class StudentRepository {

    private Map<String, Student> studentDB = new HashMap<>();
    private Map<String, Teacher> teacherDB = new HashMap<>();
    private Map<String, List<String>> studentTeacherPair = new HashMap<>();


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

        return new ArrayList<>(studentDB.keySet());
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

        if(studentDB.containsKey(student) && teacherDB.containsKey(teacher)) {

            List<String> listOfStudentOfTeacher = new ArrayList<>();

            if (studentTeacherPair.containsKey(teacher)) {
                listOfStudentOfTeacher = studentTeacherPair.get(teacher);
            }
            listOfStudentOfTeacher.add(student);
            studentTeacherPair.put(teacher, listOfStudentOfTeacher);
        }
    }

    /* <--------- Delete Methods -------------->*/

    public void deleteTeacherByName(String teacher) {


        teacherDB.remove(teacher);

        if(studentTeacherPair.containsKey(teacher)) {
            List<String> std;

            std = studentTeacherPair.get(teacher);

            for(String student : std) {

                studentDB.remove(student);
            }
            studentTeacherPair.remove(teacher);
        }
    }

    public void deleteAllTeachers() {

        HashSet<String> std_hash = new HashSet<>();

        for(String teacher : studentTeacherPair.keySet()) {

            std_hash.addAll(studentTeacherPair.get(teacher));
        }
        for(String student : std_hash) {
            studentDB.remove(student);
        }
    }

}
