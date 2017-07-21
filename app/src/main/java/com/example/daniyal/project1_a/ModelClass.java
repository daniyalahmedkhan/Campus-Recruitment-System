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
    public String type;

   ///////////////////////////

    public ModelClass(){}

    public ModelClass(String name, String email, String cgpa, String age, String bio,String type) {
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
        this.age = age;
        this.bio = bio;
        this.type = type;
    }

}
