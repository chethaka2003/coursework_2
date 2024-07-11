package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPageController {

    public Stage stage;

    @FXML
    private AnchorPane Adminpage;

    @FXML
    void Viewprojects(MouseEvent event) throws IOException{           //Create viewproject button
        System.out.println("View projects button clicked...");
        switchScene(event,"fxmls/viewProjects.fxml","stylesheets/ViewProjects.css");
    }

    //changing scenes
    private void switchScene(MouseEvent event, String fxmlPath, String cssPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void backBtn(MouseEvent event) throws IOException {         //Create backbtn button
        System.out.println("Back button clicked...");
        switchScene(event,"fxmls/welcome_user.fxml","stylesheets/scene_1.css");
    }


}

