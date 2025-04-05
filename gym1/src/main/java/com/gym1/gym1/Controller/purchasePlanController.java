package com.gym1.gym1.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.gym1.gym1.Model.Plan;
import com.gym1.gym1.Repository.planRepo;





@Controller
public class purchasePlanController {

@Autowired
private planRepo PlanRepo;





@GetMapping("/api/Plans")
    public List<Plan> getPlan(){
        List<Plan> plans = new ArrayList<>();

        Plan plan1 = new Plan(1,  "Silver Plan for 6 Months", 6, 3, 100);
        plans.add(plan1);
        Plan plan2 = new Plan(2,  "Silver Plan for 12 Months", 12, 3, 150);
        plans.add(plan2);
        Plan plan3 = new Plan(3, "Gold Plan for 6 Months", 6, 4, 150);
        plans.add(plan3);
        Plan plan4 = new Plan(4, "Gold Plan for 12 Months", 12, 4, 200);
        plans.add(plan4);
        Plan plan5 = new Plan(5, "Diamond Plan for 6 Months", 6, 5, 250);
        plans.add(plan5);
        Plan plan6 = new Plan(6, "Diamond Plan for 12 Months", 12, 5, 300);
        plans.add(plan6);

        PlanRepo.save(plan1);
        PlanRepo.save(plan2);
        PlanRepo.save(plan3);
        PlanRepo.save(plan4);
        PlanRepo.save(plan5);
        PlanRepo.save(plan6);
        return plans;
       
    }
    
    @PostMapping("/api/Plan")
    public void postPlan(@RequestBody List<Plan> plans) {
        // Save each plan in the list
        for (Plan plan : plans) {
            PlanRepo.save(plan);
        }
    }
    
   



}
    
