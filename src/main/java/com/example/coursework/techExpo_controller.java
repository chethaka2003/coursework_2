package com.example.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class techExpo_controller {

    private Stage primaryStage;


    @FXML
    private AnchorPane background;

    @FXML
    private Button loginbtn;

    @FXML
    private Button participantbtn;

    @FXML
    private Label pwd;

    @FXML
    private PasswordField pwdfield;

    @FXML
    private Label title;

    @FXML
    private Label title2;

    @FXML
    private Label username;

    @FXML
    private TextField usernamefield;

    @FXML
    //Admin click
    void loginclick(ActionEvent event) throws IOException{
        if (event.getSource() == loginbtn){
            String username = usernamefield.getText();
            String password = pwdfield.getText();
            //Checking weather username and password are correct
            if (username.equals("admin")&&password.equals("admin")){
                System.out.println("Login Successful");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/AdminPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                scene.getStylesheets().add(getClass().getResource("stylesheets/adminPage.css").toExternalForm());
                primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();

            }
            else {
                //Showing alert when incorrect password entered
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Username or Password");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void paricipantclick(ActionEvent event) throws IOException {

            if (event.getSource() == participantbtn) {
                //Checking weather random spotlight showcase happens or not
                if (!RandomSpotlightController.isPointsGive) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("fxmls/participant_home.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    scene.getStylesheets().add(getClass().getResource("stylesheets/participant.css").toExternalForm());
                    primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
                else {
                    //Showing alert when random spotlight showcase happens
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Time period to add project details are now over");
                    alert.showAndWait();

                }
            }


    }

}
