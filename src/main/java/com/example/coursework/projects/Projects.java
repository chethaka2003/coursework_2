package com.example.coursework.projects;


public class Projects {             //Creating project class

    private int project_Id;
    private String project_Name;
    private String project_Description;
    private String category;
    private String [] members;
    private String country;
    private String image_path;
    private int points;

    //Creating constructor
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

    //Getting points
    public int getPoints(){
        return this.points;
    }

    //Getting image path
    public String getImage_path() {
        return image_path;
    }

    //Changing the project name
    public void setProject_Name(String project_Name) {
        this.project_Name = project_Name;
    }

    //Changing the project description
    public void setProject_Description(String project_Description) {
        this.project_Description = project_Description;
    }

    //changing the project category
    public void setCategory(String category) {
        this.category = category;
    }

    //changing the member names
    public void setMembers(String[] members) {
        this.members = members;
    }

    //changing the country
    public void setCountry(String country) {
        this.country = country;
    }

    //Changing the image
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    //changing the points
    public void setPoints(int points){
        this.points = points;
    }

}
