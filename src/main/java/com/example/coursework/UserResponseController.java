package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UserResponseController implements Initializable {

    Stage stage;

    @FXML
    private TextArea massage;

    @FXML
    private AnchorPane userBg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        massage.setWrapText(true);
    }

    @FXML
    void sendClicked(MouseEvent event) {
        //Checking weather massage box is empty or not
        if (!massage.getText().isEmpty()) {
            try (BufferedWriter br = new BufferedWriter(new FileWriter("massage.txt", true))) {
                br.write(massage.getText());
                br.newLine();
                br.write("----------------------------------------------------------------------");
                br.newLine();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Send");
                alert.setHeaderText(null);
                alert.setContentText("Message sent successfully");
                alert.showAndWait();

                // Clear the text area after successful write
                massage.clear();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to send the message.");
                alert.showAndWait();
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid message");
            alert.showAndWait();
        }
    }

    @FXML
    void backBtnClicked(MouseEvent event) throws IOException {
        switchScene(event, "fxmls/participant_home.fxml", "stylesheets/participant.css");
    }

    @FXML
    void homeBtnClicked(MouseEvent event) throws IOException {
        switchScene(event, "fxmls/welcome_user.fxml", "stylesheets/scene_1.css");
    }

    private void switchScene(MouseEvent event, String fxmlPath, String cssPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
