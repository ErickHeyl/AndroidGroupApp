package com.example.mars.httpapp;

import org.json.JSONArray;

/**
 * Created by Mars on 3/23/16.
 */
public class User {
    String username;
    int id;
    String email;
    String firstname;
    String lastname;
    Boolean isAdmin;

    public User(String uname, int ID, String mail, String first, String last, Boolean admin){

        this.username = uname;
        this.id = ID;
        this.email = mail;
        this.firstname = first;
        this.lastname = last;
        this.isAdmin = admin;


    }
}

