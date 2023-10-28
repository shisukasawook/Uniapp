package org.uts.controller;

import org.uts.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DatabaseController {

    private static final String filename = "students.data";

    public static void initialize() {
        try {
            List<Student> students = new ArrayList<>();
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
                FileOutputStream fileOut = new FileOutputStream(filename);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(students);
                objectOut.close();
                fileOut.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, "File Not Found", ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, "Reading Error", ex);
        }
    }

    public static List<Student> readStudentsFromDatabase() {
        List<Student> students = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            students = (List<Student>) objectIn.readObject();
            objectIn.close();
            fileIn.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, "File Not Found", ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, "Reading Error", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public static void writeStudentsToDatabase(List<Student> students) {

        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(students);
            objectOut.close();
            fileOut.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, "File Not Found", ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, "Reading Error", ex);
        }
    }

    public static void updateStudentToDatabase(Student updateStudent) {
        final List<Student> students = readStudentsFromDatabase();
        List<Student> filteredStudents = students.stream()
                .filter(student -> !student.getStudentID().equals(updateStudent.getStudentID())).collect(Collectors.toList());
        filteredStudents.add(updateStudent);
        writeStudentsToDatabase(filteredStudents);
    }
}


