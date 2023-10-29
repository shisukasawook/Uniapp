package org.uts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.uts.controller.DatabaseController;

import java.io.IOException;

public class GUIUniApp extends Application {

    public static void main(String[] args) {
        DatabaseController.initialize();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setStage(primaryStage);
        Scene loginScene = new Scene(root, 350, 400);
        loginController.setScene(loginScene);
        primaryStage.setTitle("GUIUniApp");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
