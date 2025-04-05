package com.register.customer.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "purchased_plan")
public class PurchasedPlan {
    @Id
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private PayingCustomer payingCustomer;

    private String planName;
    private String duration;
    private String price;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PayingCustomer getPayingCustomer() {
        return payingCustomer;
    }

    public void setPayingCustomer(PayingCustomer payingCustomer) {
        this.payingCustomer = payingCustomer;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}