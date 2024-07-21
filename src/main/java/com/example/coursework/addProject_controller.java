package com.example.coursework;

import com.example.coursework.projects.Projects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class addProject_controller implements Initializable {

    //Countries which can choose in project
    private String[] countries = {"Srilanka","Japan","Malaysia","Thailand","India","China","USA","UK","Other"};
    private String[] categories = {" 1. Projection mapping"," 2. Virtual walls"," 3. Interactive flooring"," 4. AR and VR"," 5. AI robot"};            //Categories of the project
    private ArrayList<String> members = new ArrayList();
    private int count;

    public Stage stage;
    public static HashMap <Integer, Projects> projects = new HashMap<Integer, Projects>();     //Creating a data structure
    public String image_path;

    @FXML
    private Pane addProject;

    @FXML
    private Button addmemberBtn;

    @FXML
    private Button backbtn;

    @FXML
    private Button browsebtn;

    @FXML
    private ComboBox<String> cat_list;

    @FXML
    private Label country1;

    @FXML
    private ComboBox<String> countryCombo;

    @FXML
    private Label des1;

    @FXML
    private TextArea description;

    @FXML
    private Label logo1;

    @FXML
    private ImageView logoView;

    @FXML
    private Label member1;

    @FXML
    private ListView<String> memberList;

    @FXML
    private TextField member_name;

    @FXML
    private Label pr_cat1;

    @FXML
    private TextField pr_id;

    @FXML
    private Label pr_id1;

    @FXML
    private TextField pr_name;

    @FXML
    private Label pr_name2;

    @FXML
    private Button resetbtn;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Button submitbtn;

    @FXML
    private VBox vbox;

    private void showAlert(String title, String content) {      //Showing Alert massage
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        cat_list.getItems().addAll(categories);
        countryCombo.getItems().addAll(countries);
        pr_id.textProperty().addListener(new ChangeListener<String>() {     //checking if user is entered a integer
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    showAlert("Invalid Input","Please enter only numeric values.");
                    // Revert to the old value if the new value is invalid
                    pr_id.setText(oldValue);
                }
                else {
                    pr_id1.setText(newValue);
                }

            }
        });

        //Creating liter for project name
        pr_name.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {       //creating observer
                if (!newValue.isEmpty() && newValue.matches(".*\\d.*")) {
                    // Show an alert message
                    showAlert("Invalid Input","Please enter only letters into project name.");

                    // Revert to the old value if the new value is invalid
                    pr_name.setText(oldValue);
                }

                else if (newValue.length()>20) {            //Checking whether project name is exceeding the maximum number of characters
                    showAlert("Out of limit","You cant enter more than 20 characters into project name");
                    pr_name.setText(oldValue);
                }

                else {
                    pr_name2.setText(newValue);
                }
            }

            //Checking member name is valid or not
        });

        //Checking member name is available or not
        member_name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {      //Creating observer to check  member name
                if (newValue.matches(".*\\d.*")) {
                    showAlert("Invalid Input","Please enter only strings to the member name.");
                    member_name.setText(oldValue);
                }
                else if (newValue.length()>20) {            //Checking whether project name is exceeding the maximum number of characters
                    showAlert("Out of limit","You cant enter more than 20 characters into member name");
                    member_name.setText(oldValue);
                }
                else {
                    member_name.setText(newValue);
                }
            }
        });

        description.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length()>100) {
                    showAlert("Limit Exceeded","You cant enter more than 100 characters in description.");
                    description.setText(oldValue);
                }
                else {
                    des1.setText(newValue);
                }
            }
        });
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


    @FXML
    void addMembertoList(ActionEvent event) {
        if (count<4){           //to stop adding more than 5 members

            members.add(member_name.getText());
            memberList.getItems().clear();      //clear the input text field
            memberList.getItems().addAll(members);
            member_name.clear();
            count+=1;
            member1.setText(count+" members");
        }
        else {
            System.out.println("You can only add 4 members");       //Showing alert when trying to add more than four members
            showAlert("Limit Reached","You have added the maximum number of members");
        }

    }

    @FXML
    void removeMemberfromList(ActionEvent event) {
        if (count>0){
            members.removeLast();               //removing members from member list
            memberList.getItems().clear();
            memberList.getItems().addAll(members);
            count-=1;
            member1.setText(count+" members");
        }
        else {
            showAlert("No members","There are no members in this list to remove");
        }

    }

    //Creating file cto choose as logo
    @FXML
    void add_file(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open an image to the logo");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image","*.jpg"),new FileChooser.ExtensionFilter("PNG image","*.png"),new FileChooser.ExtensionFilter("All images","*.jpg","*.png","*.jpeg"));
        File selectedfile = fileChooser.showOpenDialog(stage);
        if (selectedfile != null){
            Image image = new Image(selectedfile.toURI().toString());
            image_path = selectedfile.toURI().toString();
            logoView.setImage(image);

        }
    }

    @FXML
    //Creating button to go  back
    void inputBack(MouseEvent event) throws IOException {           //Creating a back button
        switchScene(event,"fxmls/participant_home.fxml","stylesheets/participant.css");


    }


    @FXML
    void inputReset(ActionEvent event) {
        setFieldsEmpty();
        setLeftPannel();
    }
    private void setFieldsEmpty(){          //set All fields empty
        pr_id.setText("");
        pr_name.setText("");
        cat_list.getSelectionModel().clearSelection();
        members.clear();
        count=0;
        memberList.getItems().clear();
        description.setText("");
        countryCombo.getSelectionModel().clearSelection();
        countryCombo.setPromptText("Select your country from here");
        cat_list.setPromptText("Select a category");
        logoView.setImage(null);
    }
    private void setLeftPannel() {          //Set left panel into default state
        description.setText("");
        pr_id1.setText("Project ID");
        pr_name2.setText("Project_name");
        pr_cat1.setText("Category");
        member1.setText("Team members");
        des1.setText("Description");
        country1.setText("Country");
    }

    @FXML
    void inputSubmit(ActionEvent event) throws IOException {
        System.out.println("submit btn clicked.....");
        //Checking weather all the fields are completed or not
        if (pr_name.getText().isEmpty()||pr_id.getText().isEmpty()||cat_list.getItems().isEmpty()||description.getText().isEmpty()||countryCombo.getItems().isEmpty()||logoView.getImage()==null){
            showAlert("Complete all","Please fill all the fields in here");
        }
        else {
            //Checking if project ID is already exist or not
            if (projects.containsKey(Integer.parseInt(pr_id.getText()))){
                System.out.println("Project already exists");
                showAlert("Invalid project ID","Your project ID already exists. PLease enter a new one");
            }
            else if (count != 1) {
                Projects project = new Projects(Integer.parseInt(pr_id.getText()), pr_name.getText(), description.getText(), cat_list.getValue(), memberList.getItems().toArray(new String[0]), countryCombo.getValue(),image_path);     //Alert creating a new project
                projects.put(Integer.parseInt(pr_id.getText()), project);        //parsing project into hashmap
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);      //Showing confirmation when successfully added
                System.out.println("project added successfully");
                alert.setTitle("Successfully added");
                alert.setHeaderText(null);
                alert.setContentText("You have successfully added the project " + pr_name.getText());
                alert.showAndWait();

                saveProject(project);       //Saving project details temporally

                //Clear all project details after adding successful project
                setFieldsEmpty();
                setLeftPannel();



            } else {
                showAlert("Error 404","You must need at least two members to add a project");
            }
        }

    }

    public static void saveProject(Projects project) throws IOException {                 //Saving project into text files
        String filename;
        switch (project.getCategory()){     //Choosing the file name
            case " 1. Projection mapping":
                filename = "projection_mapping.txt";
                break;
            case " 2. Virtual walls":
                filename = "virtual_walls.txt";
                break;
            case " 3. Interactive flooring":
                filename = "interactive_flooring.txt";
                break;
            case " 4. AR and VR":
                filename = "ar_vr.txt";
                break;
            case " 5. AI robot":
                filename = "ai_robot.txt";
                break;
            default:
                filename = "other_projects.txt";
                break;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true));
        writer.write("Project ID : "+project.getProjectId()+"\n");
        writer.write("Project name : "+project.getProjectName()+"\n");
        writer.write("Category : "+project.getCategory()+"\n");
        writer.write("Team members : "+String.join(", ", project.getMembers())+"\n");
        writer.write("Description : "+project.getProjectDescription()+"\n");
        writer.write("Country : "+project.getCountry()+"\n");
        writer.write("Image path : "+project.getImage_path()+"\n");
        writer.write("----------------------------------------------------------------------------------\n");
        writer.close();
    }

    @FXML
    void homeclick(MouseEvent event)throws IOException{         //Adding a new button to go to login page
        switchScene(event,"fxmls/welcome_user.fxml","stylesheets/scene_1.css");
    }

    @FXML
    void onSelectCategory(ActionEvent event) {      //Changing the value in the left pannel
        pr_cat1.setText(cat_list.getValue());
    }

    @FXML
    void onClickCountry(ActionEvent event) {            //changing the value of left pannel
        country1.setText(countryCombo.getValue());
    }

    
}
