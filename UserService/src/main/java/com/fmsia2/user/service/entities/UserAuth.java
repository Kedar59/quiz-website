package com.fmsia2.user.service.entities;

import com.fmsia2.user.service.projections.Interaction;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usersauth")
public class UserAuth {
    @Id
    @Column(name="ID")
    private String userId;
    @Column(name="NAME")
    private String name;
    @Column(name="EMAIL",unique = true)
    private String email;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="ABOUT")
    private String about;



    public UserAuth() {
    }

    public UserAuth(String userId,String name,String email,String password,String about) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.about = about;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
