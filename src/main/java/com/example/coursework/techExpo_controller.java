package com.example.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class techExpo_controller {

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
    void loginclick(ActionEvent event) {
        if (event.getSource() == loginbtn){
            String username = usernamefield.getText();
            String password = pwdfield.getText();
            if (username.equals("admin")&&password.equals("admin")){
                System.out.println("Login Successful");
            }
            else {
                System.out.println("wrong password");
            }
        }

    }

    @FXML
    void paricipantclick(ActionEvent event) throws IOException {
        if (event.getSource() == participantbtn){
            Stage participant_stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("fxmls/participant_home.fxml"));
            Scene scene2 = new Scene(fxmlLoader.load());
//            scene2.getStylesheets().add(getClass().getResource("stylesheets/participant.css").toExternalForm());
            participant_stage.setScene(scene2);
            participant_stage.show();
        }


    }

}
