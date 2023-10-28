package org.uts.controller;

import com.diogonunes.jcolor.Attribute;
import org.uts.model.Student;
import org.uts.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.diogonunes.jcolor.Ansi.colorize;

public class StudentController {

    Scanner in;

    public StudentController() {
        in = new Scanner(System.in);
    }

    private void loginStudent() {
        System.out.println(colorize("\tStudent Sign In", Attribute.GREEN_TEXT()));
        while (true) {
            System.out.print(colorize("\tEmail: ", Attribute.BRIGHT_WHITE_TEXT()));
            final String email = in.nextLine();
            System.out.print("\tPassword: ");
            final String password = in.nextLine();
            if (validatePasswordPolicy(password) && validateEmail(email)) {
                System.out.println(colorize("\temail and password formats acceptable", Attribute.YELLOW_TEXT()));
                final List<Student> studentList = DatabaseController.readStudentsFromDatabase();
                final Student foundStudent = findStudentByEmail(email, studentList);
                if (foundStudent == null || !doPasswordsMatch(password, foundStudent.getPassword())) {
                    System.out.println(colorize("\tStudent does not exist ", Attribute.RED_TEXT()));
                    break;
                }
                startCourseMenu(foundStudent);
                break;
            } else {
                System.out.println(colorize("\tIncorrect email or password format", Attribute.RED_TEXT()));
            }
        }

    }

    private void registerStudent() {
        System.out.println(colorize("\tStudent Sign Up", Attribute.GREEN_TEXT()));
        while (true) {
            System.out.print("\tEmail: ");
            final String email = in.nextLine();
            System.out.print("\tPassword: ");
            final String password = in.nextLine();
            if (validatePasswordPolicy(password) && validateEmail(email)) {
                System.out.println(colorize("\temail and password formats acceptable", Attribute.YELLOW_TEXT()));
                final List<Student> studentList = DatabaseController.readStudentsFromDatabase();
                final Student foundStudent = findStudentByEmail(email, studentList);
                if (foundStudent != null) {
                    System.out.println(colorize(String.format("\tStudent %s %s already Exists ", foundStudent.getFirstName(), foundStudent.getLastName()), Attribute.RED_TEXT()));
                    break;
                }
                System.out.print("\tName: ");
                final String name = in.nextLine();
                final Student student = new Student();
                student.setStudentID(randomStudentID());
                student.setEmail(email);
                student.setPassword(password);
                student.setFirstName(name.split(" ")[0]);
                student.setLastName(name.split(" ")[1]);
                student.setEnrolledSubjects(new ArrayList<Subject>());
                DatabaseController.updateStudentToDatabase(student);
                System.out.println(colorize("\tEnrolling Student " + name, Attribute.YELLOW_TEXT()));
                break;
            } else {
                System.out.println(colorize("\tIncorrect email or password format", Attribute.RED_TEXT()));
            }
        }

    }

    public void startMenu() {
        while (true) {
            System.out.print(colorize("\tStudent System (l/r/x) : ", Attribute.BLUE_TEXT()));
            String input = in.nextLine();
            if (input.equals("l")) {
                loginStudent();
            } else if (input.equals("r")) {
                registerStudent();
            } else if (input.equals("x")) {
                break;
            }
        }
    }

    private String randomStudentID() {
        StringBuilder studentID = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i <= 5; i++) {
            int n = rand.nextInt(10);
            studentID.append(n);
        }
        return studentID.toString();
    }

    private boolean validatePasswordPolicy(String password) {
        Pattern pattern = Pattern.compile("^[A-Z][A-Za-z]{5,}[0-9]{3,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^.*\\..*@university.com$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private Student findStudentByEmail(String email, List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return student;
            }
        }
        return null; // Return null if the student is not found
    }

    private boolean doPasswordsMatch(String password1, String password2) {
        return password1.equals(password2);
    }

    private void changePassword(Student student) {
        System.out.println(colorize("\t\tUpdating Password", Attribute.YELLOW_TEXT()));
        while (true) {
            System.out.print("\t\tNew Password: ");
            final String newPassword = in.nextLine();
            System.out.print("\t\tConfirm Password: ");
            final String confirmPassword = in.nextLine();
            if (!newPassword.equals(confirmPassword)) {
                System.out.println(colorize("\t\tPassword does not match - try again", Attribute.RED_TEXT()));
            } else if (!validatePasswordPolicy(newPassword)) {
                System.out.println(colorize("\t\tIncorrect password format", Attribute.RED_TEXT()));
            } else {
                student.changePassword(newPassword);
                break;
            }
        }
    }

    private void enrolSubject(Student student) {
        student.enrolRandomSubject();
    }

    private void removeSubject(Student student) {
        System.out.print("\t\tRemove Subject by ID: ");
        final String subjectID = in.nextLine();
        student.removeSubjectByID(subjectID);
    }

    private void viewEnrolmentList(Student student) {
        final List<Subject> enrolledSubjects = student.getEnrolledSubjects();
        System.out.println(colorize(String.format("\t\tShowing %s subjects", enrolledSubjects.size()), Attribute.YELLOW_TEXT()));
        for (Subject subject : enrolledSubjects) {
            System.out.printf("\t\t[ Subject::%s -- mark = %s -- grade = %s ]%n", subject.getSubjectID(), subject.getSubjectMark(), subject.getSubjectGrade());
        }
    }

    private void startCourseMenu(Student loginStudent) {
        while (true) {
            System.out.print(colorize("\t\tStudent Course Menu (c/e/r/s/x) : ", Attribute.BLUE_TEXT()));
            final String input = in.nextLine();
            if (input.equals("c")) {
                changePassword(loginStudent);
            } else if (input.equals("e")) {
                enrolSubject(loginStudent);
            } else if (input.equals("r")) {
                removeSubject(loginStudent);
            } else if (input.equals("s")) {
                viewEnrolmentList(loginStudent);
            } else if (input.equals("x")) {
                break;
            }
        }
    }
}
