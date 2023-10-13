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

import static com.diogonunes.jcolor.Ansi.colorize;

public class StudentController {


    public void enrolRandomSubject(Student student) throws IOException {
        final List<Subject> subjectList = student.getEnrolledSubjects();
        if (subjectList.size() == 4) {
            System.out.println(colorize("\t\tStudents are allowed to enrol in 4 subjects only", Attribute.RED_TEXT()));
            return;
        }
        Subject subject = new Subject();
        subject.setSubjectID(generateSubjectID());
        final int subjectMark = generateSubjectMark();
        subject.setSubjectMark(subjectMark);
        final String subjectGrade = convertSubjectMarkToGrade(subjectMark);
        subject.setSubjectGrade(subjectGrade);
        subjectList.add(subject);
        student.setEnrolledSubjects(subjectList);
        DatabaseController.updateStudent(student);
        System.out.println(colorize(String.format("\t\tEnrolling in Subject-%s", subject.getSubjectMark()), Attribute.YELLOW_TEXT()));
    }

    public String convertSubjectMarkToGrade(int score) {
        if (score < 0 || score > 100) {
            return "Invalid Score";
        } else if (score < 50) {
            return "Z";
        } else if (score < 65) {
            return "P";
        } else if (score < 75) {
            return "C";
        } else if (score < 85) {
            return "D";
        } else {
            return "HD";
        }
    }

    public int generateSubjectMark() {
        Random random = new Random();
        return random.nextInt(76) + 25; // Generates a random number between 25 and 100 (inclusive)
    }


    public String generateSubjectID() {
        Random random = new Random();
        // Generate a random number between 0 and 999
        int randomNumber = random.nextInt(1000);
        // Convert the random number to a string
        return String.valueOf(randomNumber);
    }

    public Subject generateSubject() {
        Subject subject = new Subject();
        subject.setSubjectID(String.valueOf(Math.random() * 3));
        subject.setSubjectMark((int) (Math.random() * 2));
        subject.setSubjectGrade("HD");
        return subject;
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
        Pattern pattern = Pattern.compile("^[A-Z][A-Za-z]{5,}[0-9]{3,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^.*\\..*@university.com$");
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
                        loginStudent.setPassword(newPassword);
                        DatabaseController.updateStudent(loginStudent);
                        break;
                    }
                }
            } else if (input.equals("e")) {
                enrolRandomSubject(loginStudent);
            } else if (input.equals("r")) {
                System.out.print("\t\tRemove Subject by ID: ");
                final String subjectID = bufferedReader.readLine();
                loginStudent.removeSubjectByID(subjectID);
            } else if (input.equals("s")) {
                final List<Subject> enrolledSubjects = loginStudent.getEnrolledSubjects();
                System.out.println(colorize(String.format("\t\tShowing %s subjects", enrolledSubjects.size()), Attribute.YELLOW_TEXT()));
                for (Subject subject : enrolledSubjects) {
                    System.out.println(String.format("\t\t[ Subject::%s -- mark = %s -- grade = %s ]", subject.getSubjectID(), subject.getSubjectMark(), subject.getSubjectGrade()));
                }
            } else if (input.equals("x")) {
                break;
            }
        }
    }
}
