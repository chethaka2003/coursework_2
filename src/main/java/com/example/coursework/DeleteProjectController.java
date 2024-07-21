package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteProjectController {

    Stage stage;
    static int project_ID;

    @FXML
    private AnchorPane deleteBg;

    @FXML
    private TextField deleteProjectID;

    //Creating back button
    @FXML
    void Backbtn(MouseEvent event) throws IOException{
        System.out.println("Back button clicked");
        switchScene(event,"fxmls/AdminPage.fxml","stylesheets/adminPage.css");

    }

    //Creating home button
    @FXML
    void Homebtn(MouseEvent event) throws IOException{
        System.out.println("Home btn clicked");
        switchScene(event,"fxmls/welcome_user.fxml","stylesheets/scene_1.css");
    }

    private void switchScene(MouseEvent event, String fxmlPath, String cssPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loginClicked(MouseEvent event1) throws IOException{
        //Checking weather project_ID is available or not
        if (deleteProjectID.getText().isEmpty()||!addProject_controller.projects.containsKey(Integer.parseInt(deleteProjectID.getText()))) {
            showAlert("Invalid ID", "Please enter a valid project_ID");
        }
        else {
            //opening new window if project ID is correct
            project_ID = Integer.parseInt(deleteProjectID.getText());
            switchScene(event1,"fxmls/DeletingProject.fxml","stylesheets/DeletingProject.css");

        }
    }

    private void showAlert(String title, String content) {      //Showing Alert massage
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
