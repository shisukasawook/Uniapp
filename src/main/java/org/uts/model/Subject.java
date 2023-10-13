package org.uts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    private String subjectID;
    private Integer subjectMark;
    private String subjectGrade;
}
