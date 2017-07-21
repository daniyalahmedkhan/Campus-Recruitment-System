package com.example.daniyal.project1_a;

/**
 * Created by Daniyal on 7/20/2017.
 */

public class ModelClass {

   //////////Student Data/////
    public String name;
    public String email;
    public String cgpa;
    public String age;
    public String bio;

  ///////////type for activity//////////
    public String type;

   /////////////Company Data//////////////

    public String compName;
    public String compAdd;
    public String compAbout;
  /////////////////////////////

    public ModelClass(){}

    public ModelClass(String name, String email, String cgpa, String age, String bio,String type) {
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
        this.age = age;
        this.bio = bio;
        this.type = type;
    }

    public ModelClass(String compName, String compAdd, String compAbout , String type) {
        this.compName = compName;
        this.compAdd = compAdd;
        this.compAbout = compAbout;
        this.type = type;
    }
}
