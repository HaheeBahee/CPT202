package com.gym1.gym1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "usergene")
    @SequenceGenerator(sequenceName = "usergene", name="usergene", allocationSize=1)
    private int userId;
    private String userPassword;
    private String userName;
    private int userAge;
    private String userEmail;



    
    public User(int userId, String userPassword, String userName, int userAge, String userEmail) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
    }
    public User() {
    }


    
    public int getuserId() {
        return userId;
    }
    public void setuserId(int userId) {
        this.userId = userId;
    }
    public String getuserPassword() {
        return userPassword;
    }
    public void setuserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getuserName() {
        return userName;
    }
    public void setuserName(String userName) {
        this.userName = userName;
    }
    public int getuserAge() {
        return userAge;
    }
    public void setuserAge(int userAge) {
        this.userAge = userAge;
    }
    public String getuserEmail() {
        return userEmail;
    }
    public void setuserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
}
