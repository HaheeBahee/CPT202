package com.group37.project_b.model;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;
    private String planName; // Silver, Gold, Diamond
    private int planDurationMonths; // 6 or 12
    private int planStarRatingRequired; // 3 to 5
    private int cost;

    // Other plan details, getters, setters
    
    public int getPlanId() {
        return planId;
    }
    public Plan(int planId, String planName, int planDurationMonths, int planStarRatingRequired, int cost) {
        this.planId = planId;
        this.planName = planName;
        this.planDurationMonths = planDurationMonths;
        this.planStarRatingRequired = planStarRatingRequired;
        this.cost = cost;
    }
    
    public Plan() {
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    public int getPlanDurationMonths() {
        return planDurationMonths;
    }
    public void setPlanDurationMonths(int planDurationMonths) {
        this.planDurationMonths = planDurationMonths;
    }
    public int getPlanStarRatingRequired() {
        return planStarRatingRequired;
    }
    public void setPlanStarRatingRequired(int planStarRatingRequired) {
        this.planStarRatingRequired = planStarRatingRequired;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }






    
}

