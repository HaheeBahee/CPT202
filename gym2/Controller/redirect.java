package com.gym1.gym1.Controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class redirect {


    @GetMapping("/Main_page")
    public ModelAndView getMainPage() {
        return new ModelAndView("main_page");
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
        return "/main_page.html";
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

