package org.uts.controller;

import org.uts.model.Student;
import org.uts.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentController {

    public static void enrolRandomSubject(Student student) {
        Subject subject = new Subject();
        subject.setSubjectID(String.valueOf(Math.random() * 3));
        subject.setSubjectMark((int) Math.random() * 2);
        subject.setSubjectGrade("HD");

        List<Subject> subjectList = student.getEnrolledSubjects();
        subjectList.add(subject);
    }

    public static String randomStudentID() {
        String studentID = "";
        Random rand = new Random();
        for (int i = 0; i <= 5; i++) {
            int n = rand.nextInt(10);
            studentID = studentID + n;
        }
        return studentID;
    }

    public static boolean validatePasswordPolicy(String password) {
        Pattern pattern = Pattern.compile("^[A-Z][A-Za-z][A-Za-z][A-Za-z][A-Za-z][A-Za-z].*[0-9][0-9][0-9]+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(".*\\..*@university.com");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static Student findStudentByEmail(String email, List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return student;
            }
        }
        return null; // Return null if the student is not found
    }

    public static boolean doPasswordsMatch(String password1, String password2) {
        return password1.equals(password2);
    }
}
