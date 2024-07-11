package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ViewProjectsController implements Initializable {

    Set<Integer> sample;

    static int x = 72;
    static int project_count = 0;
    static int scroll_height = 650;
    static int pane_height = 650;

    @FXML
    private Pane viewprojectsBg;

    @FXML
    private ScrollPane scrollpaneBg;

    public void loadViewProjects() {
        sample = addProject_controller.projects.keySet();
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0;i<10;i++){
            createProjectIcons(String.valueOf(i));
        }
    }

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
