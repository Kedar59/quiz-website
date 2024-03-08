package com.fmsia2.user.service.entities;

import com.fmsia2.user.service.projections.Interaction;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="ID")
    private String userId;
    @Column(name="NAME",unique = true)
    private String name;
    @Column(name="EMAIL",unique = true)
    private String email;
    @Column(name="ABOUT")
    private String about;

    @Transient
    private List<Interaction> listOfInteractions = new ArrayList<>();

    public List<Interaction> getListOfInteractions() {
        return listOfInteractions;
    }

    public void setListOfInteractions(List<Interaction> listOfInteractions) {
        this.listOfInteractions = listOfInteractions;
    }

    public User() {
    }

    public User(String userId,String name,String email,String about) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.about = about;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
