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

    


}
