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

import java.util.List;
import java.util.Optional;

import static com.diogonunes.jcolor.Ansi.colorize;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private Scene scene;

    @FXML
    private void goToSubjectScene(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("subjectlist.fxml"));
            Parent root = loader.load();
            SubjectListController subjectListController = loader.getController();
            subjectListController.setLoginStudent(student);
            subjectListController.setStage(stage);
            Scene subjectlistScene = new Scene(root);
            subjectListController.setScene(subjectlistScene);
            subjectListController.setPreviousScene(scene);
            stage.setScene(subjectlistScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        String enteredEmail = emailField.getText();
        String enteredPassword = passwordField.getText();

        if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
            // Empty field(s), display an error popup
            displayErrorPopup("Empty Fields", "Please fill in both email and password fields.");
        }
        else if (!StudentController.validateEmail(enteredEmail)) {
            // Invalid email format, display an error popup
            displayErrorPopup("Invalid Email Format", "Please enter a valid email address.");
            return; // Exit the login method
        }
        else if (!StudentController.validatePasswordPolicy(enteredPassword)) {
                // Invalid email format, display an error popup
                displayErrorPopup("Invalid Password Format", "Please enter a valid password.");
                return; // Exit the login method
        }
        else{
            List<Student> students = DatabaseController.readStudentsFromDatabase();
            final Student foundStudent = StudentController.findStudentByEmail(enteredEmail, students);
            if (foundStudent == null || !StudentController.doPasswordsMatch(enteredPassword, foundStudent.getPassword())) {
                displayErrorPopup("Invalid Login Credentials", "The email or password you entered is incorrect.");
            }
            else {
                passwordField.clear();
                goToSubjectScene(foundStudent);
            }
        }
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        emailField.clear();
        passwordField.clear();
    }

    private void displayErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
    }

}




