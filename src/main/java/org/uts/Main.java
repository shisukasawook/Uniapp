package org.uts;

import org.uts.controller.DatabaseController;
import org.uts.controller.MenuController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DatabaseController.initialize();
        MenuController.displayMenu();


//        List<Student> tempList = StudentController.randomStudent();
//        DatabaseController.saveDatabase(tempList);
//
//        List<Student> studentList = DatabaseController.readDatabase();
//        for(Student student: studentList) {
//            StudentController.enrolRandomSubject(student);
//            StudentController.enrolRandomSubject(student);
//        }
//
//        DatabaseController.saveDatabase(studentList);
    }
}