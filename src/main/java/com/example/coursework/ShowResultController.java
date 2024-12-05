package com.example.coursework;

import com.example.coursework.projects.Projects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowResultController implements Initializable {

    //Creating list to sort projects according to the points
    List<Projects> sortedList = new ArrayList<>(addProject_controller.projects.values());

    Stage stage;


    @FXML
    private StackedBarChart barChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        sortingList();

        //Creating new series
        XYChart.Series series = new XYChart.Series();

        series.setName("Results");

        series.getData().add(new XYChart.Data<>(sortedList.getLast().getPoints(),sortedList.get(sortedList.size()-1).getProjectName() + ", " + sortedList.get(sortedList.size()-1).getCountry()));
        series.getData().add(new XYChart.Data<>(sortedList.get(sortedList.size()-2).getPoints(),sortedList.get(sortedList.size()-2).getProjectName() + ", " + sortedList.get(sortedList.size()-2).getCountry()));
        series.getData().add(new XYChart.Data<>(sortedList.get(sortedList.size()-3).getPoints(),sortedList.get(sortedList.size()-3).getProjectName() + ", " + sortedList.get(sortedList.size()-3).getCountry()));

        barChart.getData().add(series);
    }

    //Sorting the data in the list
    public void sortingList(){
        for(int i = 0; i < sortedList.size()-1; i++){
            for (int j = 0; j < sortedList.size()-1-i; j++){
                if (sortedList.get(j).getPoints() > sortedList.get(j+1).getPoints()){
                    Projects temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j+1));
                    sortedList.set(j+1, temp);

                }
            }
        }
    }

    @FXML
    private void backBtn(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/AdminPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("stylesheets/adminPage.css").toExternalForm());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void homeBtn(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmls/welcome_user.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("stylesheets/scene_1.css").toExternalForm());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }



}
