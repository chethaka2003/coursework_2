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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class addProject_controller implements Initializable {

    //Countries which can choose in project
    private String[] countries = {"Srilanka","Japan","Malaysia","Thailand","India","China","USA","UK","Other"};
    private String[] categories = {" 1. Projection mapping"," 2. Virtual walls"," 3. Interactive flooring"," 4 . AR and VR"," 5. AI robot"};            //Categories of the project
    private ArrayList<String> members = new ArrayList();
    private int count;

    public Stage stage;
    public HashMap <Integer, Projects> projects = new HashMap<Integer, Projects>();     //Creating a data structure
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

    @Override
    public void initialize(URL url, ResourceBundle rb){
        cat_list.getItems().addAll(categories);
        countryCombo.getItems().addAll(countries);
        pr_id.textProperty().addListener(new ChangeListener<String>() {     //checking if user is entered a integer
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);             //Showing alert if entered wrong value
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter only numeric values.");
                    alert.showAndWait();
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
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                System.out.println(oldValue);
                if (!newValue.isEmpty() && newValue.matches(".*\\d.*")) {
                    // Show an alert message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter only letters into project name.");
                    alert.showAndWait();

                    // Revert to the old value if the new value is invalid
                    pr_name.setText(oldValue);
                }

                else if (newValue.length()>20) {            //Checking whether project name is exceeding the maximum number of characters
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Out of limit");
                    alert.setHeaderText(null);
                    alert.setContentText("You cant enter more than 20 characters into project name");
                    alert.showAndWait();
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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter only strings to the member name.");
                    alert.showAndWait();
                    member_name.setText(oldValue);
                }
                else if (newValue.length()>20) {            //Checking whether project name is exceeding the maximum number of characters
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Out of limit");
                    alert.setHeaderText(null);
                    alert.setContentText("You cant enter more than 20 characters into member name");
                    alert.showAndWait();
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
                    Alert alert = new Alert(Alert.AlertType.ERROR);     //Showing alert when adding more than 100 characters
                    alert.setTitle("Limit Exceeded");
                    alert.setHeaderText(null);
                    alert.setContentText("You cant enter more than 100 characters in description.");
                    alert.showAndWait();

                    description.setText(oldValue);
                }
                else {
                    des1.setText(newValue);
                }
            }
        });
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Limit Reached");
            alert.setHeaderText(null);
            alert.setContentText("You have added the maximum number of members");
            alert.showAndWait();
        }

    }

    @FXML
    void add_file(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open an image to the logo");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image","*.jpg"),new FileChooser.ExtensionFilter("PNG image","*.png"),new FileChooser.ExtensionFilter("All images","*.jpg","*.png","*.jpeg"));
        File selectedfile = fileChooser.showOpenDialog(stage);
        if (selectedfile != null){
            Image image = new Image(selectedfile.toURI().toString());
            logoView.setImage(image);
        }
    }

    @FXML
    void inputBack(MouseEvent event) throws IOException {           //Creating a back button
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/participant_home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("stylesheets/participant.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void inputReset(ActionEvent event) {
        if (pr_id.getText().equals("")){            //Checking Project ID is empty or not
            System.out.println("You cant enter null project ID");
        }
        else {
            if (pr_id.getText().matches("//d+")){               //Checking that project Id is already exist or not
                System.out.println("Your project ID already exists");
            }
            else {
                System.out.println("Your project ID has nothing wrong");
                Projects project = new Projects(Integer.parseInt(pr_id.getText()),pr_name.getText(),description.getText(),cat_list.getValue(),memberList.getItems().toArray(new String[0]),countryCombo.getValue(),"still making");
                projects.put(Integer.parseInt(pr_id.getText()),project);

            }
        }

    }

    @FXML
    void inputSubmit(ActionEvent event) {
        System.out.println("submit btn clicked.....");
        if (count!=1){
            Projects project = new Projects(Integer.parseInt(pr_id.getText()),pr_name.getText(),description.getText(),cat_list.getValue(),memberList.getItems().toArray(new String[0]),countryCombo.getValue(),"still making");     //Alert creating a new project
            projects.put(Integer.parseInt(pr_id.getText()),project);        //parsing project into hashmap
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);      //Showing confirmation when successfully added
            alert.setTitle("Successfully added");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully added the project "+pr_name.getText());
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);     //Showing alert when there is a one member
            alert.setTitle("Error 404");
            alert.setHeaderText(null);
            alert.setContentText("You must need at least four members to add a project");
            alert.showAndWait();
        }

    }

    @FXML
    void homeclick(MouseEvent event)throws IOException{
        System.out.println("homeclick");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/welcome_user.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("stylesheets/scene_1.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
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
