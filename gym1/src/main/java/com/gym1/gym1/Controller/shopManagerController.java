package com.gym1.gym1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.gym1.gym1.Model.Shopmanager;
import com.gym1.gym1.Repository.shopManagerrepo;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class shopManagerController {

    @Autowired
    private shopManagerrepo ShopManagerRepo;
    @Autowired
    private HttpSession session;




    @GetMapping("/add_shopManager")
    public String addShopManager(Model model) {
        model.addAttribute("Shopmanager", new Shopmanager());
        return "addShopManager";
    }

    @PostMapping("/add_shopManager")
    public String addShopmanager(@ModelAttribute Shopmanager Shopmanager) {
        if (ShopManagerRepo.existsByShopManagerId(Shopmanager.getShopManagerId())) {
            
            return "/add_shopManager";
        }
        else{
      ShopManagerRepo.save(Shopmanager);
        
        return "success_forShopmanager";}
    }



    @GetMapping("/shopManagerlogin")
    public String validateLogin() {
        return "shopManagerlogin";
    }
    

    @PostMapping("/shopManagerlogin")
    public ModelAndView shopmanagerLogin(@RequestParam String shopManagerId, @RequestParam String shopManagerPassword) {
        Shopmanager shopmanager = ShopManagerRepo.findByShopManagerId(Integer.parseInt(shopManagerId));

        if (shopmanager != null && shopmanager.getShopManagerPassword().equals(shopManagerPassword)) {
           
            session.setAttribute("loggedInShopManager", shopmanager);
            return new ModelAndView("redirect:/ShopManager_page");
        } else {
            
            ModelAndView modelAndView = new ModelAndView("shopManagerlogin");
            modelAndView.addObject("shopmanagerLoginError", "Invalid ID or password");
            return modelAndView;
        }
    }



    @GetMapping("/ShopManager_page")
    public String shopManagerPage() {
        return "shopManager_Page";
    }
    
    
    
    
}
