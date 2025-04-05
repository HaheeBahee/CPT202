package com.gym1.gym1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gym1.gym1.Model.Plan;


@Repository
public interface planRepo extends JpaRepository<Plan, Integer> {
    Plan findByPlanId(int planId);

    @Query("SELECT p.planStarRatingRequired FROM Plan p WHERE p.planId = ?1")
    Integer getratingfromid(Integer id);
    
}