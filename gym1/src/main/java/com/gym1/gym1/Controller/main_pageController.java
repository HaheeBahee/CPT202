package com.gym1.gym1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class main_pageController {

    

    

    @GetMapping("/Main_page")
    public ModelAndView getMainPage() {
        return new ModelAndView("main_page");
    }
    
    

   
    
    
    

    
    




}