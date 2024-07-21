package com.example.coursework.projects;

import java.net.URI;

public class Projects {             //Creating project class

    int project_Id;
    String project_Name;
    String project_Description;
    String category;
    String [] members;
    String country;
    String image_path;
    int points;


    public Projects(int project_Id, String project_Name, String project_Description, String category, String [] members, String country, String image_path){       //Creating construction
        this.project_Id = project_Id;
        this.project_Name = project_Name;
        this.project_Description = project_Description;
        this.category = category;
        this.members = members;
        this.country = country;
        this.image_path = image_path;
    }

    //Getting category
    public String getCategory(){
        return category;
    }

    //Getting members
    public String [] getMembers(){
        return members;
    }

    //Getting project ID
    public int getProjectId(){
        return project_Id;
    }

    //Getting project name
    public String getProjectName(){
        return project_Name;
    }

    //Getting project description
    public String getProjectDescription(){
        return project_Description;
    }

    //Getting project country
    public String getCountry(){
        return country;
    }

    public int getPoints(){
        return this.points;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setProject_Id(int project_Id) {
        this.project_Id = project_Id;
    }
    public void setProject_Name(String project_Name) {
        this.project_Name = project_Name;
    }

    public void setProject_Description(String project_Description) {
        this.project_Description = project_Description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public void setCountry(String country) {
        this.country = country;

    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void setPoints(int points){
        this.points = points;
    }

}
