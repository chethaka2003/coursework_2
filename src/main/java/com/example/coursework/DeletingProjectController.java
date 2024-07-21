package com.example.coursework;

import com.example.coursework.projects.Projects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeletingProjectController implements Initializable {

    Stage stage;

    @FXML
    private AnchorPane DeletingProjectBg;

    @FXML
    private Pane showPane;

    @FXML
    private ImageView viewLogo;

    @FXML
    private Label viewProjectCategory;

    @FXML
    private Label viewProjectCountry;

    @FXML
    private Label viewProjectDescription;

    @FXML
    private Label viewProjectID;

    @FXML
    private Label viewProjectMembers;

    @FXML
    private Label viewProjectName;

    //Showing project details of deleting
    @Override
    public void initialize(URL location, ResourceBundle resources){
        startingValues(addProject_controller.projects.get(DeleteProjectController.project_ID));
    }

    @FXML
    void backBtnClicked(MouseEvent event) throws IOException{
        System.out.println("back btn clicked");
        switchScene(event,"fxmls/AdminPage.fxml","stylesheets/adminPage.css");


    }

    //Delete button clicked
    @FXML
    void deleteBtnClicked(MouseEvent event) throws IOException{
        System.out.println("delete Button Clicked...");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting..");
        alert.setHeaderText(null);
        alert.setContentText("Are you want to delete this project ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            addProject_controller.projects.remove(DeleteProjectController.project_ID);
            deleteTextfile();
            for (int i: addProject_controller.projects.keySet()){
                addProject_controller.saveProject(addProject_controller.projects.get(i));
            };
            switchScene(event,"fxmls/AdminPage.fxml","stylesheets/adminPage.css");
        }
        else {
            System.out.println("Continue editing....");
        }

    }

    //Home button clicked
    @FXML
    void homeBtnClicked(MouseEvent event) throws IOException{
        System.out.println("Home button clicked");
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

    //Setting initial values to view project
    private void startingValues(Projects project){
        viewProjectID.setText(String.valueOf(project.getProjectId()));
        viewProjectName.setText(project.getProjectName());
        viewProjectCategory.setText(project.getCategory());
        viewProjectCountry.setText(project.getCountry());
        viewProjectDescription.setText(project.getProjectDescription());
        // Populate team members in the ListView
        viewProjectMembers.setText(String.join(", ",project.getMembers()));
        Image image = new Image(project.getImage_path());
        viewLogo.setImage(image);
    }

    public static void deleteTextfile() throws IOException {
        String[] filenames = {"projection_mapping.txt", "virtual_walls.txt", "interactive_flooring.txt",
                "ar_vr.txt", "ai_robot.txt", "other_projects.txt"};
        for (String filename : filenames) {
            if (Files.exists(Paths.get(filename))) {
                FileWriter fileWriter = new FileWriter(filename, false);
                fileWriter.write("");
                fileWriter.close(); // Ensure the file writer is closed
                System.out.println("File content cleared");
            } else {
                System.out.println("File not found");
            }
        }
    }

}
