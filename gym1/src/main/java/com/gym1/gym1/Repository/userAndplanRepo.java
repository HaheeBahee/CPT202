package com.gym1.gym1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gym1.gym1.Model.Plan;
import com.gym1.gym1.Model.User;
import com.gym1.gym1.Model.UserandPlan;





@Repository
public interface userAndplanRepo extends JpaRepository<UserandPlan, Integer> {
    UserandPlan findByPlan(Plan plan);
    UserandPlan findByUser(User user);
    boolean existsByUser(User user);

    @Query("SELECT uap.plan.planId FROM UserandPlan uap WHERE uap.user.userId = ?1")
    Integer geidfromid(Integer id);

    @Query("SELECT uap FROM UserandPlan uap WHERE uap.user = ?1 ORDER BY uap.planStartedTime Desc LIMIT 1")
    UserandPlan findByUserLatest(User user);
}
