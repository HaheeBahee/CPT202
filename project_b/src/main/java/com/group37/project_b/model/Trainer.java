package com.group37.project_b.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainerId;
    private String trainerPassword;
    private String trainerName;
    private int trainerRanking;
    private String trainerIntroduction;
    private String trainerEmail;







    
    public Trainer(int trainerId, String trainerPassword, String trainerName, int trainerRanking, String trainerIntroduction) {
        this.trainerId = trainerId;
        this.trainerPassword =  trainerPassword;
        this.trainerName = trainerName;
        this.trainerRanking = trainerRanking;
        this.trainerIntroduction = trainerIntroduction;


    }
    public Trainer() {
    }

    
    public int gettrainerId() {
        return trainerId;
    }
    public void settrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
    public String gettrainerPassword() {
        return trainerPassword;
    }
    public void settrainerPassword(String trainerPassword) {
        this.trainerPassword = trainerPassword;
    }
    public String gettrainerName() {
        return trainerName;
    }
    public void settrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
    public int gettrainerRanking() {
        return trainerRanking;
    }
    public void settrainerRanking(int trainerRanking) {
        this.trainerRanking = trainerRanking;
    }
    public String gettrainerIntroduction() {
        return trainerIntroduction;
    }
    public void settrainerIntroduction(String trainerIntroduction) {
        this.trainerIntroduction = trainerIntroduction;
    }
    public String getTrainerEmail() {
        return trainerEmail;
    }
    public void setTrainerEmail(String trainerEmail) {
        this.trainerEmail = trainerEmail;
    }


    
}
