package com.gym1.gym1.Controller;

import com.gym1.gym1.Model.Appointment;
import com.gym1.gym1.Model.Trainer;
import com.gym1.gym1.Repository.userRepo;
import com.gym1.gym1.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PlanService planService;
    @Autowired
    private TrainerService trainerServicel;
    @Autowired
    private UserAndPlanService userAndPlanService;
    @Autowired
private userRepo userrepo;
    @PostMapping("/submit")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
//        System.out.println("appointment time"+appointment.getAppointmentTime());
//
//        appointment.setCreateTime(LocalDateTime.now());
//        System.out.println("Create time"+appointment.getCreateTime());
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(createdAppointment);
    }

    @GetMapping("/rating/{email}")
    public ResponseEntity<?> getRatingFromEmail(@PathVariable String email) {
    Integer rating= planService.getratingfromemail(email);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/idid/{email}")
    public ResponseEntity<?> getidfromid(@PathVariable String email){
     Integer id= userAndPlanService.getidFromid(userrepo.getUserIdByEmail(email));
        return ResponseEntity.ok(id);
    }

    @GetMapping("/trainer/{rating}")
    public List<Trainer> gettrainerbyrating(@PathVariable Integer rating){
        return trainerServicel.getTrainersByrating(rating);
    }
   @GetMapping("/trainerbyemail/{email}")
    public List<Trainer> gettrainerbyemail(@PathVariable String email){
       return trainerServicel.getTrainersByrating(planService.getratingfromemail(email));
    }
    @GetMapping("/getall/{id}")
    public List<Appointment> getallbyid(@PathVariable Integer id){
        return appointmentService.getAppointmentByuserId(id);
    }
}
