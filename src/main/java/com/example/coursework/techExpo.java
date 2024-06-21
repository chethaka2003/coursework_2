package com.example.coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class techExpo extends Application {


    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/welcome_user.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());
        scene1.getStylesheets().add(getClass().getResource("stylesheets/scene_1.css").toExternalForm());
        primaryStage.setTitle("Tech_expo 2.0");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Starting Application...");
        launch(args);
        System.out.println("Closing Application....");
    }
}
