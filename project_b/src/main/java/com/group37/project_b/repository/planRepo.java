package com.group37.project_b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group37.project_b.model.Plan;


@Repository
public interface planRepo extends JpaRepository<Plan, Integer> {
    Plan findByPlanId(int planId);
    
}