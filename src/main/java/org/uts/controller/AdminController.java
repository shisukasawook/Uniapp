package org.uts.controller;

import com.diogonunes.jcolor.Attribute;
import org.uts.model.Student;

import java.util.*;
import java.util.stream.Collectors;

import static com.diogonunes.jcolor.Ansi.colorize;

public class AdminController {

    Scanner in;

    public AdminController() {
        in = new Scanner(System.in);
    }

    private void clearData() {
        System.out.println(colorize("\t\tClearing student database", Attribute.YELLOW_TEXT()));
        System.out.print(colorize("\t\tAre you sure you want to clear database? Y(es) / N(o): ", Attribute.RED_TEXT()));
        final String confirmClear = in.nextLine();

        if (Objects.equals(confirmClear, "Y")) {
            System.out.println(colorize("\t\tStudents data cleared", Attribute.YELLOW_TEXT()));
            DatabaseController.writeStudentsToDatabase(new ArrayList<>());
        }
    }

    private void viewStudentsByGrade() {
        final List<Student> groupStudent = DatabaseController.readStudentsFromDatabase();
        System.out.println(colorize("\t\tGrade Grouping", Attribute.YELLOW_TEXT()));
        HashMap<String, List<Student>> gradeStudentMap = new HashMap<>();

        if (groupStudent.isEmpty())
            System.out.println("\t\t<Nothing to display>");

        for (Student student : groupStudent) {
            List<Student> studentList = gradeStudentMap.get(student.getAverageGrade());
            if (null == studentList) {
                studentList = new ArrayList<>();
            }
            studentList.add(student);
            gradeStudentMap.put(student.getAverageGrade(), studentList);
        }

        gradeStudentMap.forEach((grade, students) -> {
            System.out.print("\t\t" + grade + " --> ");
            List<String> gradeString = students.stream()
                    .map(student -> String.format("%s %s :: %s --> GRADE: %s - MARK: %.2f", student.getFirstName(), student.getLastName(), student.getStudentID(), student.getAverageGrade(), student.getAverageMark()))
                    .collect(Collectors.toList());
            System.out.println(gradeString);
        });
    }

    private void viewPassFailStudents() {
        final List<Student> groupStudent = DatabaseController.readStudentsFromDatabase();
        System.out.println(colorize("\t\tPASS/FAIL Partition", Attribute.YELLOW_TEXT()));

        Map<Boolean, List<String>> partitionedStudents = groupStudent.stream()
                .collect(Collectors.partitioningBy(student -> student.getAverageMark() < 50,
                        Collectors.mapping(student -> String.format("%s %s :: %s --> GRADE: %s - MARK: %.2f",
                                student.getFirstName(), student.getLastName(), student.getStudentID(),
                                student.getAverageGrade(), student.getAverageMark()), Collectors.toList())));

        System.out.print("\t\tFAIL --> ");
        System.out.println(partitionedStudents.get(true));

        System.out.print("\t\tPASS --> ");
        System.out.println(partitionedStudents.get(false));
    }

    private void removeStudent() {
        System.out.print("\t\tRemove by ID: ");
        final String studentID = in.nextLine();
        final List<Student> groupStudent = DatabaseController.readStudentsFromDatabase();
        List<Student> removeStudent = groupStudent.stream()
                .filter(student -> !student.getStudentID().equals(studentID))
                .collect(Collectors.toList());
        if (groupStudent.size() == removeStudent.size()) {
            System.out.println(colorize("\t\tStudent " + studentID + " does not exist", Attribute.RED_TEXT()));
        } else {
            System.out.println(colorize("\t\tRemoving Student " + studentID + " Account", Attribute.YELLOW_TEXT()));
            DatabaseController.writeStudentsToDatabase(removeStudent);
        }
    }

    private void viewStudents() {
        final List<Student> registeredStudents = DatabaseController.readStudentsFromDatabase();
        System.out.println(colorize("\t\tStudent List", Attribute.YELLOW_TEXT()));
        if (registeredStudents.isEmpty()) {
            System.out.println("\t\t<Nothing to display>");
        }
        for (Student student : registeredStudents) {
            System.out.printf("\t\t%s %s :: %s --> Email: %s %n", student.getFirstName(), student.getLastName(), student.getStudentID(), student.getEmail());
        }
    }


    public void startMenu() {
        while (true) {
            System.out.print(colorize("\t\tAdmin System (c/g/p/r/s/x) : ", Attribute.BLUE_TEXT()));
            String input = in.nextLine();
            if (input.equals("c")) {
                clearData();
            } else if (input.equals("g")) {
                viewStudentsByGrade();
            } else if (input.equals("p")) {
                viewPassFailStudents();
            } else if (input.equals("r")) {
                removeStudent();
            } else if (input.equals("s")) {
                viewStudents();
            } else if (input.equals("x")) {
                break;
            } else {
                System.out.println("\t\tMenu options");
                System.out.println("\t\tc = clear database file");
                System.out.println("\t\tg = group students");
                System.out.println("\t\tp = partition students");
                System.out.println("\t\tr = remove student");
                System.out.println("\t\ts = show students");
                System.out.println("\t\tx = exit");
            }
        }
    }
}


