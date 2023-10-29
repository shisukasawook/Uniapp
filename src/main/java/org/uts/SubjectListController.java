package org.uts;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.uts.controller.StudentController;
import org.uts.model.Student;
import org.uts.model.Subject;

import java.io.IOException;
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

    public void enrollButtonAction(ActionEvent event) {
        try {
            // Load the FXML for the next page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("enrolmentlist.fxml"));
            Parent root = loader.load();

            // Get the controller for the next page (if needed)
            EnrolmentController enrolmentController = loader.getController();
//            enrolmentController.setDataFromLoginScene(student);

            // Create a new stage for the next page
            Stage stage = new Stage();
            stage.setTitle("Enrol subjects");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backButtonAction(ActionEvent event) {
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.close();
    }
}
