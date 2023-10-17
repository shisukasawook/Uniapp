package org.uts;

import com.diogonunes.jcolor.Attribute;
import org.uts.controller.DatabaseController;
import org.uts.controller.MenuController;
import org.uts.controller.StudentController;
import org.uts.model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import static com.diogonunes.jcolor.Ansi.colorize;

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