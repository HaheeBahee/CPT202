package com.gym1.gym1.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gym1.gym1.Model.Trainer;
import com.gym1.gym1.Repository.trainerrepo;

import jakarta.servlet.http.HttpSession;



@Controller
public class trainerController {

    @Autowired
    private trainerrepo trainerrepo;
    @Autowired
    private HttpSession session;

    @GetMapping("/gettrainerbyid/{id}")
    public Trainer getTrainerById(@PathVariable Integer id) {
        return trainerrepo.getTrainerByid(id);
    }

    

    @GetMapping("/add_Trainer")
    public String getAddTrainer(Model model){
        model.addAttribute("Trainer", new Trainer());
        return "addTrainer";
    }

    @PostMapping("/add_Trainer")
    public String postAddTrainer(@ModelAttribute Trainer trainer){
        if(trainerrepo.existsByTrainerEmail(trainer.gettrainerEmail())) {
            // Return some indication that the email already exists, 
            // you can choose to return an error message or handle it as you prefer
            return "/add_Trainer";
        }

       else{ trainerrepo.save(trainer);
        return "success_forTrainer";   }

    }

    @PostMapping("/trainerLogin")
    public ModelAndView trainerLogin(@RequestParam String trainerEmail, @RequestParam String trainerPassword) {
        Trainer trainer = trainerrepo.findByTrainerEmail(trainerEmail);

        if (trainer != null && trainer.gettrainerPassword().equals(trainerPassword)) {
            // Trainer login successful
            // Redirect or return some response
            session.setAttribute("loggedInTrainer", trainer);
            return new ModelAndView("redirect:/trainerPage");
        } else {
            // Trainer login failed
            // Redirect back to login page with error message
            ModelAndView modelAndView = new ModelAndView("main_page");
            modelAndView.addObject("trainerLoginError", "Invalid ID or password");
            return modelAndView;
        }
    }




   
   


    @GetMapping("/trainerPage")
    public String AfterTrainerLogin() {
        return "trainerPage";
    }
    
    @GetMapping("/getTrainerData")
    @ResponseBody
    public Map<String, String> getTrainerData() {
    Map<String, String> response = new HashMap<>();
    Trainer trainer = (Trainer) session.getAttribute("loggedInTrainer");
    if (trainer != null) {
        response.put("trainerName", trainer.gettrainerName());
    } else {
        response.put("error", "Trainer not logged in");
    }
    return response;
    }

    @GetMapping("/getTrainerData1")
    @ResponseBody
    public Map<String, Integer> getTrainerData1() {
    Map<String, Integer> response = new HashMap<>();
    Trainer trainer = (Trainer) session.getAttribute("loggedInTrainer");
    if (trainer != null) {
        response.put("trainerRanking", trainer.gettrainerRanking());
    } else {
        response.put("error", 0);
    }
    return response;
    }


    
}