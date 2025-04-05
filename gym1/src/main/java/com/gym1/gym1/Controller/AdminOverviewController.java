package com.gym1.gym1.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gym1.gym1.Model.Shopmanager;
import com.gym1.gym1.Model.UserandPlan;
import com.gym1.gym1.Repository.AppointmentRepo;
import com.gym1.gym1.Repository.trainerrepo;
import com.gym1.gym1.Repository.userAndplanRepo;
import com.gym1.gym1.Repository.userRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminOverviewController {
    @Autowired
    private userAndplanRepo userAndplanRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private trainerrepo trainerrepo;

    @Autowired
    private HttpSession session;

    @GetMapping("/Admin")
    public String getAppointmentsManagementPage(Model m) {

        Shopmanager shopmanager = (Shopmanager) session.getAttribute("loggedInShopManager");
        if (shopmanager == null) {
            return "redirect:/shopManagerlogin";
        }

        m.addAttribute("ShopManagerName", shopmanager.getShopManagerName());


        List<UserandPlan> UserandPlans = userAndplanRepo.findAll(Sort.by(Sort.Direction.DESC,"planStartedTime"));

        long userCount = userRepo.count();
        long trainerCount = trainerrepo.count();
        long appointmentCount = appointmentRepo.count();

        m.addAttribute("userCount", userCount);
        m.addAttribute("trainerCount", trainerCount);
        m.addAttribute("appointmentCount", appointmentCount);

        int activePlansCounter = 0;
        int totalPlanRevenue = 0;
        for(UserandPlan uap: UserandPlans){
            totalPlanRevenue += uap.getPlan().getCost();
            if(uap.getPlanStartedTime().plusMonths(uap.getPlan().getPlanDurationMonths()).isAfter(LocalDateTime.now())){
                activePlansCounter += 1;
            }
        }

        m.addAttribute("activePlansCounter", activePlansCounter);
        m.addAttribute("totalPlanRevenue", totalPlanRevenue);
        return "/Admin/Overview";
    }
    

}
