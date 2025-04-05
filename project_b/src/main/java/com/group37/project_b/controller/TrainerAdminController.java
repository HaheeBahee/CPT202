package com.group37.project_b.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group37.project_b.model.Appointment;
import com.group37.project_b.model.Trainer;
import com.group37.project_b.repository.AppointmentRepo;
import com.group37.project_b.repository.trainerrepo;

@Controller
public class TrainerAdminController {
    @Autowired
    private trainerrepo trainerrepo;

    @Autowired 
    private AppointmentRepo appointmentRepo;

    @GetMapping("/Trainers_Management")
    public String getTrainersManagementPage(Model m){
        List<Trainer> Trainers = trainerrepo.findAll(Sort.by(Sort.Direction.ASC, "trainerId"));
        m.addAttribute("Trainers", Trainers);


        List<Appointment> Appointments = appointmentRepo.findAll();

        int counterThirty = 0;
        int counterYear = 0;
        int totalRatingThirty = 0;
        int totalRatingYear = 0;
        HashMap<Integer, List<Integer>> trainerThirty = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> trainerYear = new HashMap<Integer, List<Integer>>();
        for(Appointment a: Appointments){
            if(a.getAppointmentTime().getYear() == LocalDateTime.now().getYear()){
                counterYear++;
                totalRatingYear += a.getRating();
                if(!trainerYear.containsKey(a.getTrainer().gettrainerId())){
                    trainerYear.put(a.getTrainer().gettrainerId(), Arrays.asList(0,0));
                }
                trainerYear.get(a.getTrainer().gettrainerId()).set(0, trainerYear.get(a.getTrainer().gettrainerId()).get(0)+1);
                trainerYear.get(a.getTrainer().gettrainerId()).set(1, trainerYear.get(a.getTrainer().gettrainerId()).get(1)+a.getRating());

                if(a.getAppointmentTime().until(LocalDateTime.now(), ChronoUnit.DAYS) <= 31){
                    counterThirty++;
                    totalRatingThirty += a.getRating();
                    if(!trainerThirty.containsKey(a.getTrainer().gettrainerId())){
                        trainerThirty.put(a.getTrainer().gettrainerId(), Arrays.asList(0,0));
                    }
                    trainerThirty.get(a.getTrainer().gettrainerId()).set(0, trainerThirty.get(a.getTrainer().gettrainerId()).get(0)+1);
                    trainerThirty.get(a.getTrainer().gettrainerId()).set(1, trainerThirty.get(a.getTrainer().gettrainerId()).get(1)+a.getRating());
                }
            }
        }


        double avgRatingThirty = (double) totalRatingThirty/counterThirty;
        

        int trainerAppNoTotal = 0;

        int highestAppNoThirtyTID = -1;
        int highestAppNoThirty = -1;
        int highestTrainerAvgRatingThirtyTID = -1;
        double highestTrainerAvgRatingThirty = -1;
;
        double tempTrainerAvgRatingThirty;

        for (Integer tID : trainerThirty.keySet()){
            if(trainerThirty.get(tID).get(0) > highestAppNoThirty){
                highestAppNoThirtyTID = tID;
                highestAppNoThirty = trainerThirty.get(tID).get(0);
            }

            
            tempTrainerAvgRatingThirty = (double) trainerThirty.get(tID).get(1)/trainerThirty.get(tID).get(0);
            if(tempTrainerAvgRatingThirty > highestTrainerAvgRatingThirty){
                highestTrainerAvgRatingThirtyTID = tID;
                highestTrainerAvgRatingThirty = tempTrainerAvgRatingThirty;
            }
            trainerAppNoTotal += trainerThirty.get(tID).get(0);
        }

        double avgTrainerAppNoThirty = (double) trainerAppNoTotal/trainerThirty.size();


        
        m.addAttribute("avgTrainerAppNoThirty", avgTrainerAppNoThirty);
        m.addAttribute("avgRatingThirty", avgRatingThirty);
        m.addAttribute("highestAppNoThirtyTID", highestAppNoThirtyTID);
        m.addAttribute("highestAppNoThirty", highestAppNoThirty);
        m.addAttribute("highestTrainerAvgRatingThirtyTID", highestTrainerAvgRatingThirtyTID);
        m.addAttribute("highestTrainerAvgRatingThirty", highestTrainerAvgRatingThirty);

        

        double avgRatingYear = (double) totalRatingYear/counterYear;

        trainerAppNoTotal = 0;

        int highestAppNoYearTID = -1;
        int highestAppNoYear = -1;
        int highestTrainerAvgRatingYearTID = -1;
        double highestTrainerAvgRatingYear = -1;

        double tempTrainerAvgRatingYear;

        for (Integer tID : trainerYear.keySet()){
            if(trainerYear.get(tID).get(0) > highestAppNoYear){
                highestAppNoYearTID = tID;
                highestAppNoYear = trainerYear.get(tID).get(0);
            }

            
            tempTrainerAvgRatingYear = (double)trainerYear.get(tID).get(1)/trainerYear.get(tID).get(0);
            if(tempTrainerAvgRatingYear > highestTrainerAvgRatingYear){
                highestTrainerAvgRatingYearTID = tID;
                highestTrainerAvgRatingYear = tempTrainerAvgRatingYear;
            }
            trainerAppNoTotal += trainerYear.get(tID).get(0);
        }

        double avgTrainerAppNoYear = (double) trainerAppNoTotal/trainerYear.size();

        
        m.addAttribute("avgTrainerAppNoYear", avgTrainerAppNoYear);
        m.addAttribute("avgRatingYear", avgRatingYear);
        m.addAttribute("highestAppNoYearTID", highestAppNoYearTID);
        m.addAttribute("highestAppNoYear", highestAppNoYear);
        m.addAttribute("highestTrainerAvgRatingYearTID", highestTrainerAvgRatingYearTID);
        m.addAttribute("highestTrainerAvgRatingYear", highestTrainerAvgRatingYear);





        return "/TrainersManagement/TrainersManagement";
    }
}
