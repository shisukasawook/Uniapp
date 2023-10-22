package org.uts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject implements Serializable {
    private String subjectID;
    private Integer subjectMark;
    private String subjectGrade;

    public void generateSubjectMark() {
        Random random = new Random();
        setSubjectMark(random.nextInt(76) + 25); // Generates a random number between 25 and 100 (inclusive)
    }

    public void generateSubjectID() {
        Random random = new Random();
        // Generate a random number between 0 and 999
        int randomNumber = random.nextInt(1000);
        // Convert the random number to a string
       setSubjectID(String.format("%03d", randomNumber));
    }

    public void generateSubjectGrade() {
        String grade = convertSubjectMarkToGrade(getSubjectMark());
        setSubjectGrade(grade);
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
}

