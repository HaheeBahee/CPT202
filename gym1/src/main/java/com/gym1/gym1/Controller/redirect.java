package com.gym1.gym1.Controller;




import com.gym1.gym1.Model.Trainer;
import com.gym1.gym1.Model.User;
import com.gym1.gym1.Repository.userAndplanRepo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym1.gym1.Model.Shopmanager;

import jakarta.servlet.http.HttpSession;


@Controller

public class redirect {
    @Autowired
    private userAndplanRepo userAndplanRepo;
    @Autowired
    private HttpSession session;

    @GetMapping("/appointment")
    public String appointment() {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if(currentUser!=null){
            if(userAndplanRepo.existsByUser(currentUser) && userAndplanRepo.findByUserLatest(currentUser).getPlanStartedTime().plusMonths(userAndplanRepo.findByUserLatest(currentUser).getPlan().getPlanDurationMonths()).isAfter(LocalDateTime.now())){
                return "customer/appointment.html";
            }else{
                return "haveToPurchasePlan";
            }
        }
        return "redirect:/home";
    }
     @RequestMapping("/home")
    public String home() {
        if(session.getAttribute("loggedInUser")!=null){
            return "UserPage";
        }
        return "/Home/Home.html";
    }
    @RequestMapping("/trainer")
    public String trainer() {
        return "/Home/trainer.html";
    }
//    @RequestMapping("/login")
//    public String login() {
//        return "/login/login.html";
//    }
//    @RequestMapping("/register")
//    public String register() {
//        return "/login/register/register.html";
//    }
//    @RequestMapping("/register1")
//    public String register1() {return "/login/register/terms.html";}
//    @RequestMapping("/register2")
//    public String register2() {
//        return "/login/register/email.html";
//    }
//    @RequestMapping("/register3")
//    public String register3() {
//        return "/login/register/password.html";
//    }

    @RequestMapping("/login")
    public String login() {
        return "redirect:/Main_page";
    }
    @RequestMapping("/viewappointment")
    public String viewappointment() {
        return "/customer/viewappointment.html";
    }
    @RequestMapping("/account")
    public String account() {
        if(session.getAttribute("loggedInUser")!=null){
            return "/customer/account.html";
        }
        return "redirect:/home";
    }
    @RequestMapping("/view")
    public String view() {
        return "/customer/view.html";
    }

    @RequestMapping("/Admin/trainerRanking")
    public String trainerranking() {
        Shopmanager shopmanager = (Shopmanager) session.getAttribute("loggedInShopManager");
        if (shopmanager == null) {
            return "redirect:/shopManagerlogin";
        }
        return "/trainer/trainerrating.html";
    }


    @RequestMapping("/trainerview")
    public String trainerview() {
        return "/trainer/trainerview.html";
    }

    @RequestMapping("/")
    public String defaultPage(){
        return "redirect:/home";
    }

//    @RequestMapping("/yooheenregister")
//    public String yooheenregister() {
//        return "/userType.html";
//    }




}

