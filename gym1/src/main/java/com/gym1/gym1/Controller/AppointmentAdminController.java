package com.gym1.gym1.Controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym1.gym1.Model.Appointment;
import com.gym1.gym1.Model.AppointmentManagementDTO;
import com.gym1.gym1.Model.Shopmanager;
import com.gym1.gym1.Repository.AppointmentRepo;
import com.gym1.gym1.Repository.trainerrepo;
import com.gym1.gym1.Repository.userRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

;


@Controller
public class AppointmentAdminController {
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private trainerrepo trainerrepo;

    @Autowired
    private HttpSession session;
    
    /* @GetMapping("/api/appointments")
    public List<Appointment> getAppointments(){
        return appointmentRepo.findAll();
    } */

    @GetMapping("/Admin/Appointments_Management")
    public String getAppointmentsManagementPage(Model m) {
        Shopmanager shopmanager = (Shopmanager) session.getAttribute("loggedInShopManager");
        if (shopmanager == null) {
            return "redirect:/shopManagerlogin";
        }

        List<Appointment> Appointments = appointmentRepo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        m.addAttribute("Appointments", Appointments);
        
        int counterThirty = 0;
        int counterYear = 0;
        int totalRatingThirty = 0;
        int totalRatingYear = 0;
        int counterCompleteThirty = 0;
        int counterCompleteYear = 0;
        HashMap<Integer, List<Integer>> trainerThirty = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, List<Integer>> trainerYear = new HashMap<Integer, List<Integer>>();
        for(Appointment a: Appointments){
            if(a.getAppointmentTime().getYear() == LocalDateTime.now().getYear()){
                counterYear++;
                
                if(!trainerYear.containsKey(a.getTrainer().gettrainerId())){
                    /*map(trainerID: total appointments number, total rating of completed appointments, total number of completed appointments)*/
                    trainerYear.put(a.getTrainer().gettrainerId(), Arrays.asList(0,0,0));
                }
                trainerYear.get(a.getTrainer().gettrainerId()).set(0, trainerYear.get(a.getTrainer().gettrainerId()).get(0)+1);

                if(a.getStatus()==1){
                    counterCompleteYear += 1;
                    totalRatingYear += a.getRating();
                    trainerYear.get(a.getTrainer().gettrainerId()).set(2, trainerYear.get(a.getTrainer().gettrainerId()).get(2)+1);
                    trainerYear.get(a.getTrainer().gettrainerId()).set(1, trainerYear.get(a.getTrainer().gettrainerId()).get(1)+a.getRating());
                }

                if(a.getAppointmentTime().until(LocalDateTime.now(), ChronoUnit.DAYS) <= 31){
                    counterThirty++;
                    
                    if(!trainerThirty.containsKey(a.getTrainer().gettrainerId())){
                        /*map(trainerID: total appointments number, total rating of completed appointments, total number of completed appointments)*/
                        trainerThirty.put(a.getTrainer().gettrainerId(), Arrays.asList(0,0,0));
                    }
                    trainerThirty.get(a.getTrainer().gettrainerId()).set(0, trainerThirty.get(a.getTrainer().gettrainerId()).get(0)+1);

                    if(a.getStatus()==1){
                        counterCompleteThirty += 1;
                        totalRatingThirty += a.getRating();
                        trainerThirty.get(a.getTrainer().gettrainerId()).set(2, trainerThirty.get(a.getTrainer().gettrainerId()).get(2)+1);
                        trainerThirty.get(a.getTrainer().gettrainerId()).set(1, trainerThirty.get(a.getTrainer().gettrainerId()).get(1)+a.getRating());
                    }
                }
            }
        }


        double avgRatingThirty = (double) totalRatingThirty/counterCompleteThirty;
        

        int trainerAppNoTotal = 0;

        for (Integer tID : trainerThirty.keySet()){
            trainerAppNoTotal += trainerThirty.get(tID).get(0);
        }

        double avgTrainerAppNoThirty = (double) trainerAppNoTotal/trainerThirty.size();


        m.addAttribute("NoAppointmentThirty", counterThirty);
        m.addAttribute("avgTrainerAppNoThirty", avgTrainerAppNoThirty);
        m.addAttribute("AvgRatingThirty", avgRatingThirty);

        

        double avgRatingYear = (double) totalRatingYear/counterCompleteYear;

        trainerAppNoTotal = 0;

        for (Integer tID : trainerYear.keySet()){
            trainerAppNoTotal += trainerYear.get(tID).get(0);
        }

        double avgTrainerAppNoYear = (double) trainerAppNoTotal/trainerYear.size();

        m.addAttribute("NoAppointmentYear", counterYear);
        m.addAttribute("avgTrainerAppNoYear", avgTrainerAppNoYear);
        m.addAttribute("AvgRatingYear", avgRatingYear);



        return "/Admin/AppointmentsManagement/AppointmentsManagement";
    }

    @GetMapping("/Admin/Appointments_Management/create")
    public String getAppointmentsManagementCreatePage(Model m) {
        Shopmanager shopmanager = (Shopmanager) session.getAttribute("loggedInShopManager");
        if (shopmanager == null) {
            return "redirect:/shopManagerlogin";
        }
        
        AppointmentManagementDTO AM_DTO = new AppointmentManagementDTO();
        m.addAttribute("AM_DTO", AM_DTO);
        return "/Admin/AppointmentsManagement/AppointmentsManagementCreate";
    }

    @PostMapping("/Admin/Appointments_Management/create")
    public String postAppointmentsManagementCreatePage(
        @Valid @ModelAttribute("AM_DTO") AppointmentManagementDTO AM_DTO, 
        BindingResult result){
            Shopmanager shopmanager = (Shopmanager) session.getAttribute("loggedInShopManager");
            if (shopmanager == null) {
                return "redirect:/shopManagerlogin";
            }


            if(!userRepo.existsById(AM_DTO.getCustomerID())){
                result.addError(new FieldError("AM_DTO", "customerID", "user ID not found"));
            }

            if(!trainerrepo.existsById(AM_DTO.getTrainerID())){
                result.addError(new FieldError("AM_DTO", "trainerID", "trainer ID not found"));
            }
            
            if(result.hasErrors()){
                return "/Admin/AppointmentsManagement/AppointmentsManagementCreate";
            }

            Appointment newAppointment = new Appointment();
            newAppointment.setUser(userRepo.findByUserId(AM_DTO.getCustomerID()));
            newAppointment.setTrainer(trainerrepo.getTrainerByid(AM_DTO.getTrainerID()));
            newAppointment.setStatus(AM_DTO.getStatus());
            newAppointment.setRating(AM_DTO.getRating());
            newAppointment.setAppointmentTime(AM_DTO.getAppointmentTime());
            newAppointment.setDuration(AM_DTO.getDuration());
            newAppointment.setCreateTime(LocalDateTime.now());

            appointmentRepo.save(newAppointment);

            return "redirect:/Admin/Appointments_Management";
    }


    @GetMapping("/Admin/Appointments_Management/update")
    public String getAppointmentsManagementUpdatePage(
        Model m,
        @RequestParam int id
    ){
        Shopmanager shopmanager = (Shopmanager) session.getAttribute("loggedInShopManager");
        if (shopmanager == null) {
            return "redirect:/shopManagerlogin";
        }
        try{
            Appointment appointment = appointmentRepo.findById(id).get();
            m.addAttribute("AM", appointment);

            AppointmentManagementDTO AM_DTO = new AppointmentManagementDTO();
            AM_DTO.setCustomerID(appointment.getUser().getuserId());
            AM_DTO.setTrainerID(appointment.getTrainer().gettrainerId());
            AM_DTO.setStatus(appointment.getStatus());
            AM_DTO.setRating(appointment.getRating());
            AM_DTO.setAppointmentTime(appointment.getAppointmentTime());
            AM_DTO.setDuration(appointment.getDuration());

            m.addAttribute("AM_DTO", AM_DTO);
        }catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/Admin/Appointments_Management";
        }

        return "/Admin/AppointmentsManagement/AppointmentsManagementUpdate";
    }


    @PostMapping("/Admin/Appointments_Management/update")
    public String postAppointmentsManagementUpdatePage(
        Model m,
        @RequestParam int id,
        @Valid @ModelAttribute("AM_DTO") AppointmentManagementDTO AM_DTO, 
        BindingResult result){
            Shopmanager shopmanager = (Shopmanager) session.getAttribute("loggedInShopManager");
            if (shopmanager == null) {
                return "redirect:/shopManagerlogin";
            }

            try{
                Appointment appointment = appointmentRepo.findById(id).get();
                m.addAttribute("AM", appointment);

                if(!userRepo.existsById(AM_DTO.getCustomerID())){
                    result.addError(new FieldError("AM_DTO", "customerID", "user ID not found"));
                }
    
                if(!trainerrepo.existsById(AM_DTO.getTrainerID())){
                    result.addError(new FieldError("AM_DTO", "trainerID", "trainer ID not found"));
                }

                if(result.hasErrors()){
                    return "/Admin/AppointmentsManagement/AppointmentsManagementUpdate";
                }
                appointment.setUser(userRepo.findByUserId(AM_DTO.getCustomerID()));
                appointment.setTrainer(trainerrepo.getTrainerByid(AM_DTO.getTrainerID()));
                appointment.setStatus(AM_DTO.getStatus());
                appointment.setRating(AM_DTO.getRating());
                appointment.setAppointmentTime(AM_DTO.getAppointmentTime());
                appointment.setDuration(AM_DTO.getDuration());
                appointment.setCreateTime(LocalDateTime.now());

                appointmentRepo.save(appointment);

            }catch(Exception ex){
                System.out.println("Exception: " + ex.getMessage());
                return "redirect:/Admin/Appointments_Management";
            }

            return "redirect:/Admin/Appointments_Management";
    }
    
    @GetMapping("/Admin/Appointments_Management/delete")
    public String deleteAppointments(
        @RequestParam int id
    ){
        Shopmanager shopmanager = (Shopmanager) session.getAttribute("loggedInShopManager");
        if (shopmanager == null) {
            return "redirect:/shopManagerlogin";
        }
        try{
            Appointment appointmentToBeDeleted = appointmentRepo.findById(id).get();

            appointmentRepo.delete(appointmentToBeDeleted);
        }catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/Admin/Appointments_Management";
    }
}
 