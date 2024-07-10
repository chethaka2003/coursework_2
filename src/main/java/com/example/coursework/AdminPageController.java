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
    void Viewprojects(MouseEvent event) {           //Create viewproject button
        System.out.println("View projects button clicked...");

    }

    @FXML
    void backBtn(MouseEvent event) throws IOException {         //Create backbtn button
        System.out.println("Back button clicked...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/welcome_user.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("stylesheets/scene_1.css").toExternalForm());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

