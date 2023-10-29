package org.uts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.uts.controller.DatabaseController;
import org.uts.controller.StudentController;
import org.uts.model.Student;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class EnrolmentController {


    @FXML
    private void loginButtonAction(ActionEvent event) {

    }
    @FXML
    private void cancelButtonAction(ActionEvent event) {

    }


    private void displayErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
    }

    private void navigateToNextPage(Student student) {

    }
}




