package com.example.coursework;

import com.example.coursework.projects.Projects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ViewProjectsController implements Initializable {

    Set<Integer> sample;

    static int x = 72;          //To store the height of the pane
    static int project_count = 0;       //counting projects
    static int pane_width = 900;
    int layoutY = 92;
    int layoutX = 55;

    List<Integer> keys = new ArrayList<>(addProject_controller.projects.keySet());

    Stage stage;

    @FXML
    private ImageView back;

    @FXML
    private ImageView home;

    @FXML
    private Pane viewprojectsBg;

    @FXML
    private ScrollPane scrollpaneBg;

    //Loading projects which are saved in text files into hashmap
    public static void loadViewProjects(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        Projects project = null;

        while ((line = br.readLine()) != null) {
            if (line.startsWith("Project ID : ")) {
                // Add the previous project to the map before creating a new one
                if (project != null && !addProject_controller.projects.containsKey(project.getProjectId())) {
                    addProject_controller.projects.put(project.getProjectId(), project);
                }

                // Reset project with new Project ID
                int projectID = Integer.parseInt(line.substring("Project ID : ".length()));
                project = new Projects(projectID, null, null, null, null, null, null);

            } else if (line.startsWith("Project name : ")) {
                project.setProject_Name(line.substring("Project name : ".length()));
            } else if (line.startsWith("Category : ")) {
                project.setCategory(line.substring("Category : ".length()));
            } else if (line.startsWith("Team members : ")) {
                project.setMembers(line.substring("Team members : ".length()).split(", "));
            } else if (line.startsWith("Description : ")) {
                project.setProject_Description(line.substring("Description : ".length()));
            } else if (line.startsWith("Country : ")) {
                project.setCountry(line.substring("Country : ".length()));
            } else if (line.startsWith("Image path : ")) {
                project.setImage_path(line.substring("Image path : ".length()));
            }
        }

        // Add the last project to the map
        if (project != null && !addProject_controller.projects.containsKey(project.getProjectId())) {
            addProject_controller.projects.put(project.getProjectId(), project);
        }

        br.close();
    }

    //Adding and initialize method to run which methods need to execute from the start
    @Override
    public void initialize(URL location, ResourceBundle resources){

        //Sorting the array for get propper output
        sortedAraay();

        //Getting project by project
        for (int i: keys){
            createPane(addProject_controller.projects.get(i));

        }


    }


    //Create pane for each project respectively
    public void createPane(Projects project){

        if (project_count<1){

        }
        else {
            viewprojectsBg.setPrefWidth(pane_width+2000);
            viewprojectsBg.autosize();
        }
        Pane pane = new Pane();
        pane.setId("pane");
        pane.setPrefHeight(470);
        pane.setPrefWidth(370);
        pane.setLayoutX(layoutX);
        pane.setLayoutY(layoutY);
        viewprojectsBg.getChildren().add(pane);

        Image image = new Image(project.getImage_path());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(190);
        imageView.setFitHeight(130);
        pane.getChildren().add(imageView);
        imageView.setLayoutX(90);
        imageView.setLayoutY(34);


        Label prId= new Label("Project ID : "+project.getProjectId());
        prId.setLayoutX(31);
        prId.setLayoutY(198);
        prId.setId("project-id-view");
        pane.getChildren().add(prId);


        Label prName= new Label("Project Name : "+project.getProjectName());
        prName.setLayoutX(31);
        prName.setLayoutY(235);
        prName.setId("project-name-view");
        pane.getChildren().add(prName);


        Label prCat= new Label("Project Category : "+project.getCategory());
        prCat.setLayoutX(31);
        prCat.setLayoutY(273);
        prCat.setId("project-cat-view");
        pane.getChildren().add(prCat);


        Label prCountry= new Label("Project Country : "+project.getCountry());
        prCountry.setLayoutX(31);
        prCountry.setLayoutY(309);
        prCountry.setId("project-country-view");
        pane.getChildren().add(prCountry);


        Label prDes= new Label("Project Description : "+project.getProjectDescription());
        prDes.setLayoutX(31);
        prDes.setLayoutY(346);
        prDes.setId("project-des-view");
        pane.getChildren().add(prDes);


        Label prMembers= new Label("Team Members : "+String.join(", ",project.getMembers()));
        prMembers.setLayoutX(31);
        prMembers.setLayoutY(383);
        prMembers.setId("project-member-view");
        pane.getChildren().add(prMembers);


        layoutX+=400;
        project_count++;

    }

    @FXML
    void backbtn(MouseEvent event) throws IOException {
        switchScene(event,"fxmls/AdminPage.fxml","stylesheets/adminPage.css");
    }

    @FXML
    void homebtn(MouseEvent event) throws IOException {
        switchScene(event,"fxmls/welcome_user.fxml","stylesheets/scene_1.css");
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

    private void sortedAraay(){

        for (int i = 0; i < keys.size() - 1; i++) {
            for (int j = 0; j < keys.size() - 1 - i; j++) {
                if (keys.get(j) > keys.get(j + 1)) {
                    // Swap keys[j] and keys[j+1]
                    int temp = keys.get(j);
                    keys.set(j, keys.get(j + 1));
                    keys.set(j + 1, temp);
                }
            }
        }

    }

}
