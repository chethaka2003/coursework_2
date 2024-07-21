package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    public Stage stage;

    @FXML
    private AnchorPane Adminpage;

    //Adding and initialize method to run which methods need to execute from the start
    @Override
    public void initialize(URL location, ResourceBundle resources){
        String [] filenames = {"projection_mapping.txt", "virtual_walls.txt", "interactive_flooring.txt",
                "ar_vr.txt", "ai_robot.txt", "other_projects.txt"};

        for (String filename:filenames ) {
            if (Files.exists(Paths.get(filename))){
                try {
                    ViewProjectsController.loadViewProjects(filename);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("file is not available");
                continue;
            }
        }
    }

    @FXML
    void Viewprojects(MouseEvent event) throws IOException{//Create view project button
        if (!addProject_controller.projects.isEmpty()){
        System.out.println("View projects button clicked...");
        switchScene(event,"fxmls/viewProjects.fxml","stylesheets/ViewProjects.css");
        }
        //Showing alert if there is no project to view
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("There are no projects to show");
            alert.showAndWait();
        }
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

    @FXML
    void updateProjectbtn(MouseEvent event) throws IOException{
        System.out.println("Update project button clicked...");
        switchScene(event,"fxmls/UpdateProject.fxml","stylesheets/updateProject.css");
    }

    @FXML
    void deleteProjectClicked(MouseEvent event) throws IOException{
        System.out.println("Delete project button clicked.....");
        switchScene(event,"fxmls/DeleteProject.fxml","stylesheets/DeleteProject.css");

    }

    @FXML
    void givePointsClicked(MouseEvent event) throws IOException{
        System.out.println("Randomly give stars selected");
        switchScene(event,"fxmls/RandomSpotlight.fxml","stylesheets/RandomSpotlight.css");
    }


}

