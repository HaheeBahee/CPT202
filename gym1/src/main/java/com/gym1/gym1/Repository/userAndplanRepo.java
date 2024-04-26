package com.gym1.gym1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym1.gym1.Model.Plan;
import com.gym1.gym1.Model.User;
import com.gym1.gym1.Model.UserandPlan;





@Repository
public interface userAndplanRepo extends JpaRepository<UserandPlan, Integer> {
    UserandPlan findByPlan(Plan plan);
    UserandPlan findByUser(User user);
    boolean existsByUser(User user);
}
