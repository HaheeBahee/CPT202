package com.gym1.gym1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;



@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "trainergene")
    @SequenceGenerator(sequenceName = "trainergene", name="trainergene", allocationSize=1)
    private int trainerId;
    private String trainerPassword;
    private String trainerName;
    private int trainerRanking;
    private String trainerIntroduction;
    private String trainerEmail;







    
    public Trainer(int trainerId, String trainerPassword, String trainerName, int trainerRanking, String trainerIntroduction, String trainerEmail) {
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
    public String gettrainerEmail(){
        return trainerEmail;
    }
    public void settrainerEmail(String trainerEmail){
        this.trainerEmail=trainerEmail;
    }


    
}
