package org.uts.controller;

import com.diogonunes.jcolor.Attribute;
import org.uts.model.Student;
import org.uts.model.Subject;
import org.uts.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static com.diogonunes.jcolor.Ansi.colorize;

public class MenuController {

    public static void displayMenu() throws IOException {
        final StudentController studentController = new StudentController();
        while (true) {
            System.out.print(colorize("University System: (A)dmin, (S)tudent, or X : ", Attribute.BLUE_TEXT()));
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = bufferedReader.readLine();
            if (input.equals("A")) {
                while (true) {
                    System.out.print(colorize("\t\tAdmin System (c/g/p/r/s/x) : ", Attribute.BLUE_TEXT()));
                    input = bufferedReader.readLine();
                    if (input.equals("x")) {
                        break;
                    } else if (input.equals("s")) {
                        final List<Student> registeredStudents = DatabaseController.readDatabase();
                        System.out.println(colorize("\t\tStudent List", Attribute.YELLOW_TEXT()));
                        if (registeredStudents.isEmpty())
                            System.out.println("\t\t<Nothing to display>");

                        for (Student student : registeredStudents) {
                            System.out.println(String.format("\t\t%s %s :: %s --> Email: %s ", student.getFirstName(), student.getLastName(), student.getStudentID(), student.getEmail()));
                        }
                    } else if(input.equals("g")){
                        final List<Student> groupStudent = DatabaseController.readDatabase();
                        System.out.println(colorize("\t\tGrade Grouping", Attribute.YELLOW_TEXT()));
                            HashMap <String,List<Student>> gradeStudentMap = new HashMap<>() ;

                        if (groupStudent.isEmpty())
                            System.out.println("\t\t<Nothing to display>");

                        for (Student student : groupStudent)
                        {
                            List<Student> studentList = gradeStudentMap.get(student.getAverageGrade());
                            if(null == studentList){
                                studentList = new ArrayList<>();
                            }
                            studentList.add(student);
                            gradeStudentMap.put(student.getAverageGrade(),studentList);
                        }

                        gradeStudentMap.forEach((grade, students) -> {
                            System.out.print("\t\t" + grade + " --> ");
                           //students.forEach(student-> System.out.print(String.format("[%s %s :: %s --> GRADE: %s - MARK: %s]",student.getFirstName(),student.getLastName(),student.getStudentID(),student.getAverageGrade(),student.getAverageMark())));


                            List<String> gradeString = students.stream()
                                    .map(student -> String.format("%s %s :: %s --> GRADE: %s - MARK: %s",student.getFirstName(),student.getLastName(),student.getStudentID(),student.getAverageGrade(),student.getAverageMark()))
                                    .collect(Collectors.toList());


                            System.out.println(gradeString);

                        });

                    } else if(input.equals("p")){
//                        final List<Student> groupStudent = DatabaseController.readDatabase();
//                        System.out.println(colorize("\t\tPASS/FAIL Partition", Attribute.YELLOW_TEXT()));
//
//                        System.out.print("\t\tFAIL --> ");
//                        List<String> failStudents = groupStudent.stream()
//                                .filter(student -> student.getAverageMark() < 65)
//                                .map(student -> String.format("%s %s :: %s --> GRADE: %s - MARK: %s",student.getFirstName(),student.getLastName(),student.getStudentID(),student.getAverageGrade(),student.getAverageMark()))
//                                .collect(Collectors.toList());
//
//                        System.out.println(failStudents);
//                        System.out.print("\t\tPASS --> ");
//
//                        List<String> passStudents = groupStudent.stream()
//                                .filter(student -> student.getAverageMark() >= 65)
//                                .map(student -> String.format("%s %s :: %s --> GRADE: %s - MARK: %s",student.getFirstName(),student.getLastName(),student.getStudentID(),student.getAverageGrade(),student.getAverageMark()))
//                                .collect(Collectors.toList());
//
//                        System.out.println(passStudents);

                        final List<Student> groupStudent = DatabaseController.readDatabase();
                        System.out.println(colorize("\t\tPASS/FAIL Partition", Attribute.YELLOW_TEXT()));

                        Map<Boolean, List<String>> partitionedStudents = groupStudent.stream()
                                .collect(Collectors.partitioningBy(student -> student.getAverageMark() < 65,
                                        Collectors.mapping(student -> String.format("%s %s :: %s --> GRADE: %s - MARK: %.2f",
                                                student.getFirstName(), student.getLastName(), student.getStudentID(),
                                                student.getAverageGrade(), student.getAverageMark()), Collectors.toList())));

                        System.out.print("\t\tFAIL --> ");
                        System.out.println(partitionedStudents.get(true));

                        System.out.print("\t\tPASS --> ");
                        System.out.println(partitionedStudents.get(false));

                     }else if(input.equals("r")){
                        System.out.print("\t\tRemove by ID: ");
                        final String studentID = bufferedReader.readLine();
                        final List<Student> groupStudent = DatabaseController.readDatabase();
                        List<Student> removeStudent = groupStudent.stream()
                                .filter(student -> !student.getStudentID().equals(studentID))
                                .collect(Collectors.toList());
                        if (groupStudent.size() == removeStudent.size()){
                            System.out.println(colorize("\t\tStudent " + studentID + " does not exist", Attribute.RED_TEXT()));
                        } else
                        {
                            System.out.println(colorize("\t\tRemoving Student " + studentID + " Account", Attribute.YELLOW_TEXT()));
                            DatabaseController.saveDatabase(removeStudent);
                        }

                    }

                else if(input.equals("c")){
                    System.out.println(colorize("\t\tClearing student database", Attribute.YELLOW_TEXT()));
                    System.out.print(colorize("\t\tAre you sure you want to clear database? Y(es) / N(o): ", Attribute.RED_TEXT()));
                        final String confirmClear = bufferedReader.readLine();

                    if (Objects.equals(confirmClear, "Y")){
                        System.out.println(colorize("\t\tStudents data cleared", Attribute.YELLOW_TEXT()));
                        DatabaseController.saveDatabase(new ArrayList<>());
                    }

                }

                }


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
                                final List<Student> studentList = DatabaseController.readDatabase();
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
                                DatabaseController.updateStudent(student);
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
                                final List<Student> studentList = DatabaseController.readDatabase();
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
}
