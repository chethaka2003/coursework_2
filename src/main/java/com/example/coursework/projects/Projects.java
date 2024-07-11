package com.example.coursework.projects;

public class Projects {             //Creating project class

    int project_Id;
    String project_Name;
    String project_Description;
    String category;
    String [] members;
    String country;
    String image_path;


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

    public String getImage_path() {
        return image_path;
    }
}
