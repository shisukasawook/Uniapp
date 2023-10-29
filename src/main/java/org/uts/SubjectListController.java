package org.uts;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.uts.controller.StudentController;
import org.uts.model.Student;
import org.uts.model.Subject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SubjectListController implements Initializable {
    private Student loginStudent;
    @FXML
    private ListView<String> subjectListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setDataFromLoginScene(Student student){
        loginStudent = student;

        final List<Subject> subjectList = loginStudent.getEnrolledSubjects();
        List<String> subjectIDList = subjectList.stream()
                .map(subject -> String.format("%s", subject.getSubjectID()))
                .collect(Collectors.toList());
        subjectListView.setItems(FXCollections.observableArrayList(subjectIDList));

    }
}
