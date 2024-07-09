package com.example.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class addProject_controller implements Initializable {

    private String[] categories = {" 1. Projection mapping"," 2. Virtual walls"," 3. Interactive flooring"," 4 . AR and VR"," 5. AI robot"};
    private ArrayList<String> members = new ArrayList();
    private int count;
    private String[] countries = {"Srilanka","Japan","Malaysia","Thailand","India","China","USA","UK","Other"};
    public Stage stage;

    public int projectID;
    public String projectName;

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


    }


    @FXML
    void addMembertoList(ActionEvent event) {
        if (count<4){           //to stop adding more than 5 members

            members.add(member_name.getText());
            memberList.getItems().clear();      //clear the input text field
            memberList.getItems().addAll(members);
            member_name.clear();
            count+=1;
        }
        else
        { System.out.println("You can only add 4 members");}

    }

    @FXML
    void add_file(ActionEvent event) {

    }

    @FXML
    void inputBack(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/participant_home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("stylesheets/participant.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void inputReset(ActionEvent event) {
        try {
            projectID = Integer.parseInt(pr_id.getText());
            pr_id1.setTextFill(Paint.valueOf("#008000"));
        } catch (NumberFormatException e){
            System.out.println("Please enter numbers only");
            pr_id1.setTextFill(Paint.valueOf("#FF0000"));
        }
//        if (!pr_id.getText().equals("")) {
//            pr_id1.setTextFill(Paint.valueOf("#FF0000"));
//        }

    }

    @FXML
    void inputSubmit(ActionEvent event) {

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

}
