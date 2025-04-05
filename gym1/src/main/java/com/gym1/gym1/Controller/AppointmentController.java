package com.gym1.gym1.Controller;

import com.gym1.gym1.Model.Appointment;
import com.gym1.gym1.Model.Trainer;
import com.gym1.gym1.Repository.AppointmentRepo;
import com.gym1.gym1.Repository.userRepo;
import com.gym1.gym1.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PlanService planService;
    @Autowired
    private TrainerService trainerServicel;
    @Autowired
    private UserAndPlanService userAndPlanService;
    @Autowired
    private UseService useService;
@Autowired
private userRepo userrepo;
    @PostMapping("/submit")
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) {
//        System.out.println("appointment time"+appointment.getAppointmentTime());
//
//        appointment.setCreateTime(LocalDateTime.now());
//        System.out.println("Create time"+appointment.getCreateTime());
        try{
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(createdAppointment);}
        catch (Exception e){
            String errorMessage = "无法创建预约：" + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
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
    @PostMapping("/updateappointment")
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointmentDTO) {
        Optional<Appointment> existingAppointment = appointmentRepo.findById(appointmentDTO.getId());
        if (existingAppointment.isPresent()) {
            // 存在该预约信息，调用服务层方法更新预约信息
            appointmentService.updateAppointment(appointmentDTO);
            return ResponseEntity.ok("预约信息已成功更新");
        } else {
            // 不存在该预约信息，返回错误响应
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found with ID: " + appointmentDTO.getId());
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable("id") int id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/alltrainer")
    public List<Trainer> getAllTrainer(){
        return trainerServicel.getAllTrainers();
    }
    @PutMapping("/trainer/{trainerId}/{ranking}")
    public ResponseEntity<?> changeTrainerRanking(@PathVariable Integer trainerId, @PathVariable Integer ranking) {
        Trainer trainer = trainerServicel.getTrainerByid(trainerId);
        if (trainer != null) {
            trainer.settrainerRanking(ranking);
            trainerServicel.changeRating(trainer);
            return ResponseEntity.ok(trainer); // 返回更新后的训练师对象
        } else {
            return ResponseEntity.notFound().build(); // 没有找到相关的训练师，返回404
        }
    }

    @DeleteMapping("/deletetrainer/{trainerId}")
    public ResponseEntity<?> deleteTrainer(@PathVariable Integer trainerId) {
        // Call your service method to delete the trainer
        boolean deleted = trainerServicel.deletetrainer(trainerId);
        if (deleted) {
            return ResponseEntity.ok("Trainer deleted successfully");
        } else {
            System.out.println("Trainer not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getappointment/trainer/{id}")
    public List<Appointment> getAppointmentByTrainerId(@PathVariable Integer id) {
        return appointmentService.getAppointmentByTrainerId(id);
    }

    @PutMapping("makecomplete/{id}")
    public ResponseEntity<?> makeComplete(@PathVariable Integer id){
        Appointment appointment = appointmentRepo.getAppointmtbyappointmentid(id);
        if(appointment != null){
            appointment.setStatus(1);
            appointmentService.updateAppointment(appointment);
            return ResponseEntity.ok("Appointment completed successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/submitRating/{id}")
    public ResponseEntity<?> submitRating(@PathVariable Integer id, @RequestBody Integer rating) {
        Appointment appointment = appointmentRepo.getAppointmtbyappointmentid(id);

        if (appointment != null) {
            System.out.println("Appointment found");
            appointment.setRating(rating);
            appointmentRepo.save(appointment);

            // 返回 JSON 对象
            Map<String, String> response = new HashMap<>();
            response.put("message", "Rating submitted successfully");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}

