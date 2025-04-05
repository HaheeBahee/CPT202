package com.gym1.gym1.Model;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class AppointmentManagementDTO {

    @NotNull(message = "Customer ID is required")
    @Min(0)
    private int customerID;

    @NotNull(message = "Trainer ID is required")
    @Min(0)
    private int trainerID;

    @Min(0)
    @Max(2)
    private int status; // 0 - new, 1 - completed, 2 - cancelled

    @Min(0)
    @Max(5)
    private int rating;

    @NotNull(message = "An appointment time is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime appointmentTime;

    @Min(0)
    private int duration;   //in minutes

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public int getStatus() {
        return status;
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


    
}
