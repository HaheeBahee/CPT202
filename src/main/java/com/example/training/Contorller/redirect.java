package com.example.training.Contorller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class redirect {
    @GetMapping("/appointment")
    public String appointment() {
        return "customer/appointment.html";
    }
    @RequestMapping("/home")
    public String home() {
        return "/Home/Home.html";
    }
    @RequestMapping("/trainer")
    public String trainer() {
        return "/Home/trainer.html";
    }
    @RequestMapping("/login")
    public String login() {
        return "/login/login.html";
    }
    @RequestMapping("/register")
    public String register() {
        return "/login/register/register.html";
    }
    @RequestMapping("/register1")
    public String register1() {return "/login/register/terms.html";}
    @RequestMapping("/register2")
    public String register2() {
        return "/login/register/email.html";
    }
    @RequestMapping("/register3")
    public String register3() {
        return "/login/register/password.html";
    }
    @RequestMapping("/viewappointment")
    public String viewappointment() {
        return "/customer/viewappointment.html";
    }
    @RequestMapping("/account")
    public String account() {
        return "/customer/account.html";
    }
    @RequestMapping("/view")
    public String view() {
        return "/customer/view.html";
    }







}

