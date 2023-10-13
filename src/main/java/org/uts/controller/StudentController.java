package org.uts.controller;

import com.diogonunes.jcolor.Attribute;
import org.uts.model.Student;
import org.uts.model.Subject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.diogonunes.jcolor.Ansi.colorize;

public class StudentController {

    public void enrolRandomSubject(Student student) {
        Subject subject = new Subject();
        subject.setSubjectID(String.valueOf(Math.random() * 3));
        subject.setSubjectMark((int) (Math.random() * 2));
        subject.setSubjectGrade("HD");
        List<Subject> subjectList = student.getEnrolledSubjects();
        subjectList.add(subject);
    }

    public String randomStudentID() {
        StringBuilder studentID = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i <= 5; i++) {
            int n = rand.nextInt(10);
            studentID.append(n);
        }
        return studentID.toString();
    }

    public boolean validatePasswordPolicy(String password) {
        Pattern pattern = Pattern.compile("^[A-Z][A-Za-z][A-Za-z][A-Za-z][A-Za-z][A-Za-z].*[0-9][0-9][0-9]+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(".*\\..*@university.com");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public Student findStudentByEmail(String email, List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return student;
            }
        }
        return null; // Return null if the student is not found
    }

    public boolean doPasswordsMatch(String password1, String password2) {
        return password1.equals(password2);
    }

    public void start(Student loginStudent) throws IOException {
        while (true) {
            System.out.print(colorize("\t\tStudent Course Menu (c/e/r/s/x) : ", Attribute.BLUE_TEXT()));
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            final String input = bufferedReader.readLine();
            if (input.equals("c")) {
                System.out.println(colorize("\t\tUpdating Password", Attribute.YELLOW_TEXT()));
                while (true) {
                    System.out.print("\t\tNew Password: ");
                    final String newPassword = bufferedReader.readLine();
                    System.out.print("\t\tConfirm Password: ");
                    final String confirmPassword = bufferedReader.readLine();
                    if (!newPassword.equals(confirmPassword)) {
                        System.out.println(colorize("\t\tPassword does not match - try again", Attribute.RED_TEXT()));
                    } else if (!validatePasswordPolicy(newPassword)) {
                        System.out.println(colorize("\t\tIncorrect password format", Attribute.RED_TEXT()));
                    } else {
                        final List<Student> studentList = DatabaseController.readDatabase();
                        List<Student> filteredList = studentList.stream()
                                .filter(student -> !student.getStudentID().equals(loginStudent.getStudentID())).collect(Collectors.toList());
                        loginStudent.setPassword(newPassword);
                        filteredList.add(loginStudent);
                        DatabaseController.saveDatabase(filteredList);
                        break;
                    }
                }
            } else if (input.equals("e")) {
                break;
            } else if (input.equals("r")) {
                break;
            } else if (input.equals("s")) {
                break;
            } else if (input.equals("x")) {
                break;
            }
        }
    }
}
