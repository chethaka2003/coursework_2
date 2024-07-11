package com.example.coursework;

import com.example.coursework.projects.Projects;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;

public class ViewProjectsController implements Initializable {

    Set<Integer> sample;

    static int x = 72;          //To store the height of the pane
    static int project_count = 0;       //counting projects
    static int pane_height = 650;

    // Declare the variables outside the if-else structure
    int projectID = 0;
    String projectName = null;          //project detail variables
    String category = null;
    String[] teamMembers = null;
    String description = null;
    String country = null;
    String imagePath = null;

    @FXML
    private Pane viewprojectsBg;

    @FXML
    private ScrollPane scrollpaneBg;

    //Loading projects which are saved in text files into hashmap
    public void loadViewProjects(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            if(line.startsWith("Project ID : ")){
                projectID = Integer.parseInt(line.substring("Project ID : ".length()));

            }
            else if(line.startsWith("Project name : ")){
                projectName = line.substring("Project name : ".length());
            }
            else if(line.startsWith("Category : ")){
                category = line.substring("Category : ".length());
            }
            else if(line.startsWith("Team members : ")){
                teamMembers = line.substring("Team members : ".length()).split(", ");
            }
            else if(line.startsWith("Description ")){
                description = line.substring("Description : ".length());
            }
            else if(line.startsWith("Country : ")){
                country = line.substring("Country : ".length());
            }
            else if(line.startsWith("Image path : ")){
                imagePath = line.substring("Image path : ".length());
            }
            else{

            }

            if (!addProject_controller.projects.containsKey(projectID)){
                Projects project = new Projects(projectID,projectName,description,category,teamMembers,country,imagePath);
                addProject_controller.projects.put(projectID,project);
            }
            else {
                continue;
            }
        }
    }

    //Adding and initialize method to run which methods need to execute from the start
    @Override
    public void initialize(URL location, ResourceBundle resources){

        String [] filenames = {"projection_mapping.txt", "virtual_walls.txt", "interactive_flooring.txt",
                "ar_vr.txt", "ai_robot.txt", "other_projects.txt"};

            for (String filename:filenames ) {
                if (Files.exists(Paths.get(filename))){
                try {
                    loadViewProjects(filename);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                }
                else {
                    System.out.println("file is not available");
                    continue;
                }
            }


        for (int i :addProject_controller.projects.keySet()){
            createProjectIcons(String.valueOf(i));;
        }
    }

    //Creating project icons for each
    public void createProjectIcons(String btnNAme){
        if (project_count<8) {
        }
        else {
            viewprojectsBg.setPrefHeight(pane_height+200);

        }
        Button btn = new Button(btnNAme);
        btn.setPrefSize(100, 50);
        btn.setId("project-icons");
        btn.setLayoutX(397);
        btn.setLayoutY(x);
        x += 70;
        viewprojectsBg.getChildren().add(btn);
        project_count++;
    }

}
