package com.group37.project_b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group37.project_b.model.Plan;
import com.group37.project_b.model.User;
import com.group37.project_b.model.UserandPlan;





@Repository
public interface userAndplanRepo extends JpaRepository<UserandPlan, Integer> {
    UserandPlan findByPlan(Plan plan);
    UserandPlan findByUser(User user);
    boolean existsByUser(User user);
}
