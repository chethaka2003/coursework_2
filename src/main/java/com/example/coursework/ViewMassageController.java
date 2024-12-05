package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewMassageController implements Initializable {

    Stage stage;

    @FXML
    private TextArea massageShow;

    @FXML
    private AnchorPane msgView;


    @FXML
    private Pane msgBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Showing the massages
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("massage.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("----")) {
                    content.append(line).append("\n\n");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        massageShow.setText(content.toString());
        massageShow.setWrapText(true);
        massageShow.setEditable(false);


    }

    @FXML
    void deleteClicked(MouseEvent event) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("massage.txt", false))) {
            bufferedWriter.write("");
        }
        massageShow.setText("");
    }

    @FXML
    void backBtnClicked(MouseEvent event) throws IOException {
        switchScene(event, "fxmls/AdminPage.fxml", "stylesheets/adminPage.css");
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
