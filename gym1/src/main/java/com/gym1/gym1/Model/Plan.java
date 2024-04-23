package com.gym1.gym1.Model;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;

@Entity
public class Plan {
    @Id
    private int planId;
    private String planName; // Silver, Gold, Diamond
    private int planDurationMonths; // 6 or 12
    private int planStarRatingRequired; // 3 to 5
    // Other plan details, getters, setters
    
    public int getPlanId() {
        return planId;
    }
    public Plan(int planId, String planName, int planDurationMonths, int planStarRatingRequired) {
        this.planId = planId;
        this.planName = planName;
        this.planDurationMonths = planDurationMonths;
        this.planStarRatingRequired = planStarRatingRequired;
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






    
}

