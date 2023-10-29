package org.uts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Set the FXML controller (if not specified in the FXML file)
        // SampleController controller = loader.getController();

        // Create a scene with the loaded FXML content
        Scene scene = new Scene(root, 400, 200);

        // Set the stage title
        primaryStage.setTitle("JavaFX Example");

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();



    }
}
