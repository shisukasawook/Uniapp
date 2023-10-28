package org.uts.controller;

import com.diogonunes.jcolor.Attribute;
import org.uts.model.Student;
import org.uts.model.Subject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class CLIUniApp {
    Scanner in;

    public CLIUniApp() {
        in = new Scanner(System.in);
    }

    public void startMenu() throws IOException {
        final StudentController studentController = new StudentController();
        while (true) {
            System.out.print(colorize("University System: (A)dmin, (S)tudent, or X : ", Attribute.BRIGHT_CYAN_TEXT()));
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = bufferedReader.readLine();
            if (input.equals("A")) {
                displayAdminSystem();
            } else if (input.equals("S")) {
                while (true) {
                    System.out.print(colorize("\tStudent System (l/r/x) : ", Attribute.BLUE_TEXT()));
                    input = bufferedReader.readLine();
                    if (input.equals("x")) {
                        break;
                    } else if (input.equals("r")) {
                        System.out.println(colorize("\tStudent Sign Up", Attribute.GREEN_TEXT()));
                        while (true) {
                            System.out.print("\tEmail: ");
                            final String email = bufferedReader.readLine();
                            System.out.print("\tPassword: ");
                            final String password = bufferedReader.readLine();
                            if (studentController.validatePasswordPolicy(password) && studentController.validateEmail(email)) {
                                System.out.println(colorize("\temail and password formats acceptable", Attribute.YELLOW_TEXT()));
                                final List<Student> studentList = DatabaseController.readStudentsFromDatabase();
                                final Student foundStudent = studentController.findStudentByEmail(email, studentList);
                                if (foundStudent != null) {
                                    System.out.println(colorize(String.format("\tStudent %s %s already Exists ", foundStudent.getFirstName(), foundStudent.getLastName()), Attribute.RED_TEXT()));
                                    break;
                                }
                                System.out.print("\tName: ");
                                final String name = bufferedReader.readLine();
                                final Student student = new Student();
                                student.setStudentID(studentController.randomStudentID());
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

                    } else if (input.equals("l")) {
                        System.out.println(colorize("\tStudent Sign In", Attribute.GREEN_TEXT()));
                        while (true) {
                            System.out.print("\tEmail: ");
                            final String email = bufferedReader.readLine();
                            System.out.print("\tPassword: ");
                            final String password = bufferedReader.readLine();
                            if (studentController.validatePasswordPolicy(password) && studentController.validateEmail(email)) {
                                System.out.println(colorize("\temail and password formats acceptable", Attribute.YELLOW_TEXT()));
                                final List<Student> studentList = DatabaseController.readStudentsFromDatabase();
                                final Student foundStudent = studentController.findStudentByEmail(email, studentList);
                                if (foundStudent == null || !studentController.doPasswordsMatch(password, foundStudent.getPassword())) {
                                    System.out.println(colorize("\tStudent does not exist ", Attribute.RED_TEXT()));
                                    break;
                                }
                                studentController.start(foundStudent);
                                break;
                            } else {
                                System.out.println(colorize("\tIncorrect email or password format", Attribute.RED_TEXT()));
                            }
                        }
                    }

                }
            } else if (input.equals("X")) {
                System.out.println(colorize("Thank You", Attribute.YELLOW_TEXT()));
                break;
            } else {
                System.out.println("Menu options");
                System.out.println("A = Admin System");
                System.out.println("S = Student System");
                System.out.println("X = Exit");
            }
        }
    }

    private void displayAdminSystem() {
        AdminController adminController = new AdminController();
        adminController.startMenu();
    }
}
