package org.uts.model;

import com.diogonunes.jcolor.Attribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.uts.controller.DatabaseController;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends User implements Serializable {

    private String studentID;
    private List<Subject> enrolledSubjects;

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
            DatabaseController.updateStudent(this);
        }
        System.out.println(colorize(String.format("\t\tYou are now enrolled in %s out of 4 subjects", subjectList.size()), Attribute.YELLOW_TEXT()));
    }
}
