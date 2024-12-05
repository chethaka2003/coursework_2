package com.example.coursework;

import com.example.coursework.projects.Projects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UpdatingProjectController implements Initializable {

    //Variables to hold project details temporary
    String Category = "";
    String country = "";
    String projectName = "";
    String projectDescription = "";
    String imagePath = "";
    //Assigning variable to count the members count
    int count = addProject_controller.projects.get(UpdateProjectController.project_ID).getMembers().length;
    ObservableList<String> teamMembers = FXCollections.observableArrayList(addProject_controller.projects.get(UpdateProjectController.project_ID).getMembers());

    //Defining the values in combo boxes
    private String[] countries = {"Srilanka","Japan","Malaysia","Thailand","India","China","USA","UK","Other"};
    private String[] categories = {" 1. Projection mapping"," 2. Virtual walls"," 3. Interactive flooring"," 4. AR and VR"," 5. AI robot"};            //Categories of the project

    Stage stage;

    @FXML
    private AnchorPane UpdatingBg;

    @FXML
    private ComboBox<String> newProjectCategory;

    @FXML
    private ComboBox<String> newProjectCountry;

    @FXML
    private TextArea newProjectDescription;

    @FXML
    private Label newProjectId;

    @FXML
    private TextField newProjectName;

    @FXML
    private TextField newTeamMember;

    @FXML
    private Pane sampleView;

    @FXML
    private Label viewProjectCategory;

    @FXML
    private Label viewProjectCountry;

    @FXML
    private Label viewProjectDescription;

    @FXML
    private Label viewProjectName;

    @FXML
    private ListView<String> viewTeamMembers;

    @FXML
    private ImageView viewlogo;

    //Initialize method to continue the same rules which has in adding project
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newProjectCategory.getItems().addAll(categories);
        newProjectCountry.getItems().addAll(countries);
        startingValues(addProject_controller.projects.get(UpdateProjectController.project_ID));

        //Avoiding getting numbers as project name
        newProjectName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.matches(".*\\d.*")){
                    showAlert("Invalid input","Please enter String values to project name");
                    newProjectName.setText(oldValue);
                }
                else if (newValue.length()>20) {            //Checking whether project name is exceeding the maximum number of characters
                    showAlert("Out of limit","You cant enter more than 20 characters into project name");
                    newProjectName.setText(oldValue);
                }
                else{
                    viewProjectName.setText(newValue);
                    projectName = newProjectName.getText();
                }

            }
        });

        //Checking description characters are more than 100 or not
        newProjectDescription.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length()>100){
                    showAlert("Limit Reached","You cant'd add more than 100 characters");
                    newProjectDescription.setText(oldValue);
                }
                else {
                    viewProjectDescription.setText(newValue);
                    projectDescription = newProjectDescription.getText();
                }
            }
        });

        //Checking weather team member name contains any integer or not
        newTeamMember.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.matches(".*\\d.*")){
                    showAlert("Invalid input","Please enter String values to team member");
                }

            }
        });
    }

    //Changing the logo
    @FXML
    void ChangeLogobtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a new logo");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image","*.jpg"),new FileChooser.ExtensionFilter("PNG image","*.png"),new FileChooser.ExtensionFilter("ALL image","*.png","*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            viewlogo.setImage(image);
            imagePath = selectedFile.toURI().toString();
            System.out.println(imagePath);
        }
    }

    //Adding members into listview
    @FXML
    void addMemberBtn(ActionEvent event) {
        //Showing alert when count exceed four
        if (count>=4){
            showAlert("Limit Reached","You cant add more than 4 members");
        }
        else if (newTeamMember.getText().isEmpty()){
            showAlert("Invalid input","Please enter values to team member name");
        }
        else {
            String newMemberName = newTeamMember.getText();
            teamMembers.add(newMemberName);
            viewTeamMembers.setItems(teamMembers);
            newTeamMember.clear();
            count++;
            System.out.println(teamMembers);
            System.out.println(count);
        }

    }

    //adding back button
    @FXML
    void backbtn(MouseEvent event) throws IOException{
        System.out.println("Back button clicked");
        switchScene(event,"fxmls/AdminPage.fxml","stylesheets/adminPage.css");
    }

    //Adding home button
    @FXML
    void homebtn(MouseEvent event) throws IOException{
        System.out.println("Home button clicked");
        switchScene(event,"fxmls/welcome_user.fxml","stylesheets/scene_1.css");
    }

    //removing members from the list
    @FXML
    void removeMemberBtn(ActionEvent event) {
        if (teamMembers.isEmpty()){
            showAlert("No members","You have not added any members to this project");
        }
        else {
            teamMembers.removeLast();
            count--;
            System.out.println(teamMembers);
        }
    }

    //Showing alerts
    private void showAlert(String title, String content) {      //Showing Alert massage
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    //Setting initial values to view project
    private void startingValues(Projects project){
        newProjectId.setText(String.valueOf(project.getProjectId()));
        viewProjectName.setText(project.getProjectName());
        viewProjectCategory.setText(project.getCategory());
        viewProjectCountry.setText(project.getCountry());
        viewProjectDescription.setText(project.getProjectDescription());
        // Populate team members in the ListView
        viewTeamMembers.setItems(teamMembers);
        Image image = new Image(project.getImage_path());
        viewlogo.setImage(image);
    }

    //Setting values to country
    @FXML
    void onClickCategory(ActionEvent event) {
        viewProjectCategory.setText(newProjectCategory.getValue());
        Category = newProjectCategory.getValue();
    }

    //Setting values to country
    @FXML
    void onClickCountry(ActionEvent event) {
        viewProjectCountry.setText(newProjectCountry.getValue());
        country = newProjectCountry.getValue();
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

    //Adding a submit button
    @FXML
    void submitBtnClicked(MouseEvent event) throws IOException {
        //Checking weather there are at least two members
        if (teamMembers.size()<=1){
            showAlert("404 Error","You must have at least two members to each team");
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to Update this project?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //Updating the project details into hashmap after getting user confirmation
                updateProject(addProject_controller.projects.get(UpdateProjectController.project_ID));
                //delete all the text files
                deleteTextfile();
                //saving every project into text file again
                for (int i : addProject_controller.projects.keySet()) {
                    addProject_controller.saveProject(addProject_controller.projects.get(i));
                }
                ;
                //automatically go to the previous page
                switchScene(event, "fxmls/AdminPage.fxml", "stylesheets/adminPage.css");
            } else {
                System.out.println("continue editing...");
            }
        }
    }

    //Updating the each project detail
    void updateProject(Projects project){
        if (!projectName.isEmpty()){
            project.setProject_Name(projectName);
        }
        if(!Category.isBlank()){
            project.setCategory(Category);
        }
        if(!country.isEmpty()){
            project.setCountry(country);
        }
        if(!teamMembers.isEmpty()){
            project.setMembers(teamMembers.toArray(new String[teamMembers.size()]));
        }
        if(!imagePath.isEmpty()){
            project.setImage_path(imagePath);
        }
        if (!projectDescription.isEmpty()){
            project.setProject_Description(projectDescription);
        }
        System.out.println("project updated");
    }

    //deleting every details in text files
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
