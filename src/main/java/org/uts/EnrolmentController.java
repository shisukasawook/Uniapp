package org.uts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.uts.model.Student;
import org.uts.model.Subject;

import java.util.List;
import java.util.Optional;

public class EnrolmentController {

    Student loginStudent;
    private Stage stage;
    private Scene previousScene;
    private Scene scene;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void goBack() {
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
    }

    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    @FXML
    private void enrolButtonAction(ActionEvent event) {
        final List<Subject> subjectList = loginStudent.getEnrolledSubjects();
        if (subjectList.size() == 4) {
            displayErrorPopup("xxxxxxxxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxxxxxxxxxx");
        } else {
            try {
                loginStudent.enrolRandomSubject(subjectList);
                displayErrorPopup("SUccesss", "SUccesss");
            } catch (Exception e) {
            }
        }
    }
    @FXML
    private void backButtonAction(ActionEvent event) {
        goBack();
    }

    public void setLoginStudent(Student student) {
        loginStudent = student;
    }


    private void displayErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
    }
}




