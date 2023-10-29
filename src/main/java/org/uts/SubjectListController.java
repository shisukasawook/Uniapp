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
    private Stage stage;
    private Scene previousScene;
    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void goToEnrolScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("enrolmentlist.fxml"));
            Parent root = loader.load();
            EnrolmentController enrolmentController = loader.getController();
            enrolmentController.setLoginStudent(loginStudent);
            enrolmentController.setStage(stage);
            Scene enrolmentScene = new Scene(root);
            enrolmentController.setScene(enrolmentScene);
            enrolmentController.setPreviousScene(scene);
            stage.setScene(enrolmentScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void logout() {
        goBack();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    private void goBack() {
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
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
        goToEnrolScene();
    }

}
