package com.group37.project_b.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "trainerId")
    private Trainer trainer;
    private int status; // 0 - new, 1 - completed, 2 - cancelled
    private int rating;
    private LocalDateTime appointmentTime;
    private int duration;   //in minutes
    private LocalDateTime createTime;


    public Appointment() {
    }


    

    public Appointment(int id, User user, Trainer trainer, int status, int rating, LocalDateTime appointmentTime,
            int duration, LocalDateTime createTime) {
        this.id = id;
        this.user = user;
        this.trainer = trainer;
        this.status = status;
        this.rating = rating;
        this.appointmentTime = appointmentTime;
        this.duration = duration;
        this.createTime = createTime;
    }




    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Trainer getTrainer() {
        return trainer;
    }


    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }


    public int getStatus() {
        return status;
    }

    public String getStatusText() {
        if(this.status==0){
            return "UNCOMPLETED";
        }
        else if(this.status==1){
            return "COMPLETED";
        }
        else if(this.status==2){
            return "CANCELLED";
        }
        return "ERROR";
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public int getRating() {
        return rating;
    }


    public void setRating(int rating) {
        this.rating = rating;
    }


    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }


    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }


    public int getDuration() {
        return duration;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }


    public LocalDateTime getAppointmentEndTime(){
        return this.appointmentTime.plusMinutes(this.duration);
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }


    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getTimeInterval(){
        DateTimeFormatter localTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return this.appointmentTime.format(localTimeFormat) +" ~ "+this.appointmentTime.plusMinutes(this.duration).format(localTimeFormat);
    }
    
    public String getFormattedCreateTime(){
        DateTimeFormatter localTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSSSSSSSS");
        return this.createTime.format(localTimeFormat);
    }
    
}
