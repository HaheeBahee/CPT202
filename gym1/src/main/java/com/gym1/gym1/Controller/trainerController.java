package com.gym1.gym1.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gym1.gym1.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gym1.gym1.Model.Trainer;
import com.gym1.gym1.Model.User;
import com.gym1.gym1.Repository.trainerrepo;

import jakarta.servlet.http.HttpSession;



@RestController
public class trainerController {

    @Autowired
    private trainerrepo trainerrepo;
    @Autowired
    private HttpSession session;
    @Autowired
    private TrainerService trainerService;

    @GetMapping("/gettrainerbyid/{id}")
    public Trainer getTrainerById(@PathVariable Integer id) {
        return trainerrepo.getTrainerByid(id);
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

    @GetMapping("/getTrainerid")
    @ResponseBody
    public Map<String, Integer> getTrainerid() {
        Map<String, Integer> response = new HashMap<>();
        Trainer trainer = (Trainer) session.getAttribute("loggedInTrainer");
        if (trainer != null) {
            response.put("trainerid", trainer.gettrainerId());
        } else {
            response.put("error", -1);
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