package org.uts.model;

import com.diogonunes.jcolor.Attribute;

import org.uts.controller.DatabaseController;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;


public class Student extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentID;
    private List<Subject> enrolledSubjects;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public List<Subject> getEnrolledSubjects() {
        return enrolledSubjects;
    }

    public void setEnrolledSubjects(List<Subject> enrolledSubjects) {
        this.enrolledSubjects = enrolledSubjects;
    }

    public void enrolRandomSubject() throws IOException {
        final List<Subject> subjectList = getEnrolledSubjects();
        if (subjectList.size() == 4) {
            System.out.println(colorize("\t\tStudents are allowed to enrol in 4 subjects only", Attribute.RED_TEXT()));
            return;
        }
        Subject subject = new Subject();
        subject.generateSubjectID();
        subject.generateSubjectMark();
        subject.generateSubjectGrade();
        subjectList.add(subject);
        setEnrolledSubjects(subjectList);
        DatabaseController.updateStudentToDatabase(this);
        System.out.println(colorize(String.format("\t\tEnrolling in Subject-%s", subject.getSubjectID()), Attribute.YELLOW_TEXT()));
    }

    public void removeSubjectByID(String id) throws IOException {
        final List<Subject> subjectList = getEnrolledSubjects();
        Subject subjectToRemove = null;
        for (Subject subject : subjectList) {
            if (subject.getSubjectID().equals(id)) {
                subjectToRemove = subject;
                break;
            }
        }
        if (subjectToRemove != null) {
            System.out.println(colorize(String.format("\t\tDropping Subject-%s", subjectToRemove.getSubjectID()), Attribute.YELLOW_TEXT()));
            subjectList.remove(subjectToRemove);
            this.setEnrolledSubjects(subjectList);
            DatabaseController.updateStudentToDatabase(this);
        }
        System.out.println(colorize(String.format("\t\tYou are now enrolled in %s out of 4 subjects", subjectList.size()), Attribute.YELLOW_TEXT()));

    }

        public double getAverageMark( ) {
            if (enrolledSubjects.isEmpty()) {
                return 0.0; // You can choose to return 0 or handle this case differently.
            }

            int sum = 0;
            for (Subject subject : enrolledSubjects) {
                sum += subject.getSubjectMark();
            }

            return (double) sum / enrolledSubjects.size();
        }

    public String getAverageGrade( ) {
        return convertAverageMarkToGrade (getAverageMark()) ;
    }

    public String convertAverageMarkToGrade(double getAverageMark) {
        if (getAverageMark < 0 || getAverageMark > 100) {
            return "Invalid Score";
        } else if (getAverageMark < 50) {
            return "Z";
        } else if (getAverageMark < 65) {
            return "P";
        } else if (getAverageMark < 75) {
            return "C";
        } else if (getAverageMark < 85) {
            return "D";
        } else {
            return "HD";
        }


}}
