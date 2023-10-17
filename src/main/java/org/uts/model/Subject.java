package org.uts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject implements Serializable {
    private String subjectID;
    private Integer subjectMark;
    private String subjectGrade;
}
