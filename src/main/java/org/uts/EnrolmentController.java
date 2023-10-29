package org.uts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.uts.model.Student;

import java.util.Optional;

public class EnrolmentController {

    private Stage stage;

    private Scene previousScene;
    private Scene scene;

    public void setStage(Stage stage) {
        stage.setTitle("Enrol subjects");
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

    }
    @FXML
    private void backButtonAction(ActionEvent event) {
        goBack();
    }


    private void displayErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
    }
}




