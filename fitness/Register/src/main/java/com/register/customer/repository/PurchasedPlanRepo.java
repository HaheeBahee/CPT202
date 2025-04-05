package com.register.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.register.customer.model.PurchasedPlan;

@Repository
public interface PurchasedPlanRepo extends JpaRepository<PurchasedPlan, String> {
    PurchasedPlan findByEmail(String email);

}
