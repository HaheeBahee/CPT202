package com.gym1.gym1.Controller;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym1.gym1.Model.Shopmanager;
import com.gym1.gym1.Model.UserandPlan;
import com.gym1.gym1.Repository.userAndplanRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Admin")
public class BusinessStatsController {
    @Autowired
    private userAndplanRepo userAndplanRepo;

    @Autowired
    private HttpSession session;

    @GetMapping("/Statistics")
    public String getAppointmentsManagementPage(Model m) {
        Shopmanager shopmanager = (Shopmanager) session.getAttribute("loggedInShopManager");
        if (shopmanager == null) {
            return "redirect:/shopManagerlogin";
        }


        List<UserandPlan> UserandPlans = userAndplanRepo.findAll(Sort.by(Sort.Direction.DESC,"planStartedTime"));


        int[] planRevenue = new int[12];
        for(int i=0;i<planRevenue.length;i++){
            planRevenue[i] = 0;
        }


        for(UserandPlan uap: UserandPlans){
            if(uap.getPlanStartedTime().isAfter(LocalDateTime.now().minusYears(1))){
                planRevenue[12 - 1 - ((int) ChronoUnit.MONTHS.between(YearMonth.from(uap.getPlanStartedTime()), YearMonth.from(LocalDateTime.now())))] += uap.getPlan().getCost();
            }
        }

        double[] planRevenuePercentChange = new double[planRevenue.length-1];
        for(int i=0;i<planRevenuePercentChange.length;i++){
            planRevenuePercentChange[i] = planRevenue[i]==0? planRevenue[i+1]==0?0:100 : (double)(((planRevenue[i+1]-planRevenue[i])/(planRevenue[i])) * 100);
        }

        m.addAttribute("planRevenue", planRevenue);
        m.addAttribute("planRevenuePercentChange", planRevenuePercentChange);
        return "/Admin/BusinessStats/BusinessStats.html";
    }
    
}
