package com.example.coursework;

import com.example.coursework.projects.Projects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RandomSpotlightController implements Initializable {


    Stage stage;        //To switch between scenes

    //control variable to stop accessing APD,VPD,UPD
    public static boolean isPointsGive = false;

    //Creating hashmaps for Each project category
    public HashMap <Integer, Projects> category_1 = new HashMap<Integer,Projects>();
    public HashMap <Integer, Projects> category_2 = new HashMap<Integer,Projects>();
    public HashMap <Integer, Projects> category_3 = new HashMap<Integer,Projects>();
    public HashMap <Integer, Projects> category_4 = new HashMap<Integer,Projects>();
    public HashMap <Integer, Projects> category_5 = new HashMap<Integer,Projects>();

    //Arraylist to shuffle projects
    ArrayList<Integer> shuffledAllKeys = new ArrayList<Integer>();

    //Adding star count for each judge
    static int judge_1 = 0;
    static int judge_2 = 0;
    static int judge_3 = 0;
    static int judge_4 = 0;

    //Average point of the judges
    int total;

    //Shuffle project ID index
    static int shuffledIndex = 0;

    //Random


    @FXML
    private ImageView j1s1;

    @FXML
    private ImageView j1s2;

    @FXML
    private ImageView j1s3;

    @FXML
    private ImageView j1s4;

    @FXML
    private ImageView j1s5;

    @FXML
    private ImageView j2s1;

    @FXML
    private ImageView j2s2;

    @FXML
    private ImageView j2s3;

    @FXML
    private ImageView j2s4;

    @FXML
    private ImageView j2s5;

    @FXML
    private ImageView j3s1;

    @FXML
    private ImageView j3s2;

    @FXML
    private ImageView j3s3;

    @FXML
    private ImageView j3s4;

    @FXML
    private ImageView j3s5;

    @FXML
    private ImageView j4s1;

    @FXML
    private ImageView j4s2;

    @FXML
    private ImageView j4s3;

    @FXML
    private ImageView j4s4;

    @FXML
    private ImageView j4s5;

    @FXML
    private Label newProjectId;

    @FXML
    private AnchorPane randomBg;

    @FXML
    private Pane showPane;

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

    @Override
    public void initialize (URL url, ResourceBundle rs){

        //Changing the control variable
        isPointsGive = true;

        //Loading project details from text files
        try {
            if(Files.exists(Paths.get("projection_mapping.txt"))) {
                loadViewProjects("projection_mapping.txt", category_1);
            }
            if(Files.exists(Paths.get("virtual_walls.txt"))) {
                loadViewProjects("virtual_walls.txt", category_2);
            }
            if(Files.exists(Paths.get("interactive_flooring.txt"))) {
                loadViewProjects("interactive_flooring.txt", category_3);
            }
            if(Files.exists(Paths.get("ar_vr.txt"))) {
                loadViewProjects("ar_vr.txt", category_4);
            }
            if(Files.exists(Paths.get("ai_robot.txt"))) {
                loadViewProjects("ai_robot.txt", category_5);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        //Putting all hashmaps in array
        HashMap[] allHashMap = {category_1, category_2, category_3, category_4, category_5};

        for (HashMap i: allHashMap){
            shuffledAllKeys.addAll(shuffleKeys(i));
        }

        //adding initial project to view
        ChangingPane(addProject_controller.projects.get(shuffledAllKeys.get(shuffledIndex)));



    }

    //Remove judge 4 stars
    @FXML
    void judgeFourRemove(MouseEvent event) {
        judge_4 = removing_stars(judge_4,j4s1,j4s2,j4s3,j4s4,j4s5);
    }

    //Add judge 4 stars
    @FXML
    void judgeFouradd(MouseEvent event) {
        judge_4 = giving_stars(judge_4,j4s1,j4s2,j4s3,j4s4,j4s5);
    }

    //Remove judge 1 stars
    @FXML
    void judgeOneRemove(MouseEvent event) {
        judge_1 = removing_stars(judge_1,j1s1,j1s2,j1s3,j1s4,j1s5);
    }

    //Add judge 1 stars
    @FXML
    void judgeOneadd(MouseEvent event) {
        judge_1 = giving_stars(judge_1,j1s1,j1s2,j1s3,j1s4,j1s5);

    }

    //Remove judge 3 stars
    @FXML
    void judgeThreeRemove(MouseEvent event) {
        judge_3 = removing_stars(judge_3,j3s1,j3s2,j3s3,j3s4,j3s5);
    }

    //Add judge 3 stars
    @FXML
    void judgeThreeadd(MouseEvent event) {
        judge_3 = giving_stars(judge_3,j3s1,j3s2,j3s3,j3s4,j3s5);

    }

    //Remove judge 2 stars
    @FXML
    void judgetwoRemove(MouseEvent event) {
        judge_2 = removing_stars(judge_2,j2s1,j2s2,j2s3,j2s4,j2s5);
        System.out.println(judge_2);
    }

    //Add judge 2 stars
    @FXML
    void judgetwoadd(MouseEvent event) {
        judge_2 = giving_stars(judge_2,j2s1,j2s2,j2s3,j2s4,j2s5);
        System.out.println(judge_2);
    }

    //Loading next item when Button is pressed
    @FXML
    void nextItem(MouseEvent event) throws IOException {
        //Checking weather list is end or not
        try {
            //Preventing skipping projects without stars
            if (judge_1!=0||judge_2!=0||judge_3!=0||judge_4!=0){shuffledIndex+=1;
                total = (judge_1+judge_2+judge_3+judge_4);
                //adding points to the project
                addProject_controller.projects.get(shuffledAllKeys.get(shuffledIndex)).setPoints(total);
                //reset all the values again into zero
                judge_1 = 0;
                judge_2 = 0;
                judge_3 = 0;
                judge_4 = 0;
                resetStars();
                total = 0;
                //Changing into next pane
                ChangingPane(addProject_controller.projects.get(shuffledAllKeys.get(shuffledIndex)));
                System.out.println(addProject_controller.projects.get(shuffledAllKeys.get(shuffledIndex)).getPoints());
            }
            else {
                //Showing alert when try skip project
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No stars given");
                alert.setHeaderText(null);
                alert.setContentText("You must give at least one star to each project");
                alert.showAndWait();
            }

        }catch (IndexOutOfBoundsException e){
            //showing alert when index is out of range
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Completed");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully given stars to all projects");
            alert.showAndWait();
            switchScene(event,"fxmls/AdminPage.fxml","stylesheets/adminPage.css");

        }
    }

    //Loading projects which are saved in text files into particular hashmap
    public static void loadViewProjects(String filename,HashMap obj) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        Projects project = null;

        while ((line = br.readLine()) != null) {
            if (line.startsWith("Project ID : ")) {
                // Add the previous project to the map before creating a new one
                if (project != null && !obj.containsKey(project.getProjectId())) {
                    obj.put(project.getProjectId(), project);
                }

                // Reset project with new Project ID
                int projectID = Integer.parseInt(line.substring("Project ID : ".length()));
                project = new Projects(projectID, null, null, null, null, null, null);

            } else if (line.startsWith("Project name : ")) {
                project.setProject_Name(line.substring("Project name : ".length()));
            } else if (line.startsWith("Category : ")) {
                project.setCategory(line.substring("Category : ".length()));
            } else if (line.startsWith("Team members : ")) {
                project.setMembers(line.substring("Team members : ".length()).split(", "));
            } else if (line.startsWith("Description : ")) {
                project.setProject_Description(line.substring("Description : ".length()));
            } else if (line.startsWith("Country : ")) {
                project.setCountry(line.substring("Country : ".length()));
            } else if (line.startsWith("Image path : ")) {
                project.setImage_path(line.substring("Image path : ".length()));
            }
        }

        // Add the last project to the map
        if (project != null && !obj.containsKey(project.getProjectId())) {
            obj.put(project.getProjectId(), project);
        }

        br.close();
    }

    //Adding stars
    public int giving_stars(int judge,ImageView s1,ImageView s2,ImageView s3,ImageView s4,ImageView s5){
        if (judge>=5){
            judge=4;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Limit exceed");
            alert.setHeaderText(null);
            alert.setContentText("You cant give more than five stars");
            alert.showAndWait();
        }
        judge+=1;
        if (judge == 1){
            //changing opacity
            s1.setOpacity(1);
        }
        else if(judge == 2){
            s1.setOpacity(1);
            s2.setOpacity(1);
        }
        else if(judge == 3){
            s1.setOpacity(1);
            s2.setOpacity(1);
            s3.setOpacity(1);
        }
        else if(judge == 4){
            s1.setOpacity(1);
            s2.setOpacity(1);
            s3.setOpacity(1);
            s4.setOpacity(1);
        }
        else if(judge == 5){
            s1.setOpacity(1);
            s2.setOpacity(1);
            s3.setOpacity(1);
            s4.setOpacity(1);
            s5.setOpacity(1);
        }
        return judge;
    }

    //Removing stars
    public int removing_stars(int judge,ImageView s1,ImageView s2,ImageView s3,ImageView s4,ImageView s5){

        if (judge<=0){
            judge=1;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Out of range");
            alert.setHeaderText(null);
            alert.setContentText("No stars to remove");
            alert.showAndWait();

        }
        judge-=1;
        if (judge == 0){
            //changing opacity
            s1.setOpacity(0.2);
            s2.setOpacity(0.2);
            s3.setOpacity(0.2);
            s4.setOpacity(0.2);
            s5.setOpacity(0.2);
        }
        else if (judge == 1){
            s1.setOpacity(1);
            s2.setOpacity(0.2);
            s3.setOpacity(0.2);
            s4.setOpacity(0.2);
            s5.setOpacity(0.2);
        }
        else if(judge == 2){
            s1.setOpacity(1);
            s2.setOpacity(1);
            s3.setOpacity(0.2);
            s4.setOpacity(0.2);
            s5.setOpacity(0.2);
        }
        else if(judge == 3){
            s1.setOpacity(1);
            s2.setOpacity(1);
            s3.setOpacity(1);
            s4.setOpacity(0.2);
            s5.setOpacity(0.2);
        }
        else if(judge == 4){
            s1.setOpacity(1);
            s2.setOpacity(1);
            s3.setOpacity(1);
            s4.setOpacity(1);
            s5.setOpacity(0.2);
        }
        else if(judge == 5){
            s1.setOpacity(1);
            s2.setOpacity(1);
            s3.setOpacity(1);
            s4.setOpacity(1);
            s5.setOpacity(1);
        }
        return judge;
    }

    //Changing pane
    private void ChangingPane(Projects project){

        ObservableList<String> teamMembers = FXCollections.observableArrayList(project.getMembers());
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

    // Method to shuffle the keys in a HashMap
    public List<Integer> shuffleKeys(HashMap<Integer, Projects> original) {
        List<Integer> keys = new ArrayList<>(original.keySet());
        Collections.shuffle(keys);
        return keys;
    }

    //switch scene
    private void switchScene(MouseEvent event, String fxmlPath, String cssPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    //Setting all stars into default style
    public void resetStars() {
        ImageView[] stars = {j1s1, j1s2, j1s3, j1s4, j1s5, j2s1, j2s2, j2s3, j2s4, j2s5, j3s1, j3s2, j3s3, j3s4, j3s5, j4s1, j4s2, j4s3, j4s4, j4s5};
        for (ImageView i : stars) {
            i.setOpacity(0.2);
        }
    }

}




