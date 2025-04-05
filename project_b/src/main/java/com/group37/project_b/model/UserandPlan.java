package com.group37.project_b.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserandPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;
    private long planStartedTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Plan plan;



    

    public UserandPlan(int purchaseId, long planStartedTime, User user, Plan plan) {
        this.purchaseId = purchaseId;
        this.planStartedTime = planStartedTime;
        this.user = user;
        this.plan = plan;
    }

    public UserandPlan() {
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public long getPlanStartedTime() {
        return planStartedTime;
    }

    public void setPlanStartedTime(long planStartedTime) {
        this.planStartedTime = planStartedTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    

   
}
