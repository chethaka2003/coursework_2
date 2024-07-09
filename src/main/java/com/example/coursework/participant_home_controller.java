package com.example.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class participant_home_controller {

    public Stage stage;


    @FXML
    private Button addproj;

    @FXML
    private Label addtext;

    @FXML
    private Label back2;

    @FXML
    private Button back2home;

    @FXML
    private Label req2chnge;

    @FXML
    private Button reqChange;


    @FXML
    void onClickAddProj(MouseEvent event) throws IOException{

            System.out.println("add project button clicked");
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("fxmls/addProject.fxml"));
            Scene scene = new Scene(fxmlloader.load());
            scene.getStylesheets().add(getClass().getResource("stylesheets/addProject.css").toExternalForm());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

    }

    @FXML
    void onClickBack2Home(MouseEvent event) throws IOException {

            System.out.println("Back to home button clicked");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/welcome_user.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("stylesheets/scene_1.css").toExternalForm());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


    }

    @FXML
    void onClickReqChange(MouseEvent event) throws IOException {

            System.out.println("request to change button clicked");





    }





}
