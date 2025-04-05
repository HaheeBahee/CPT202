package com.gym1.gym1.Controller;

import com.gym1.gym1.Model.Plan;
import com.gym1.gym1.Model.User;
import com.gym1.gym1.Model.UserandPlan;
import com.gym1.gym1.Repository.planRepo;
import com.gym1.gym1.Repository.userAndplanRepo;
import com.gym1.gym1.Repository.userRepo;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;





@Controller
public class userController {

    @Autowired
    private userRepo userrepo;

    @Autowired
    private HttpSession session;

    @Autowired
    private planRepo PlanRepo;

    @Autowired
    private userAndplanRepo UserAndPlanRepo;
     @GetMapping("/getUserIdByEmail/{email}")
    public ResponseEntity<?> getUserIdByEmail(@PathVariable String email) {
        Integer userId = userrepo.getUserIdByEmail(email);
        return ResponseEntity.ok(userId);
    }
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userrepo.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }



    @GetMapping("/Users_page")
    public String getuserPage(Model m) {
        List<User> Users = userrepo.findAll();
        m.addAttribute("Users", Users);
        return "main";
    }

    @GetMapping("/add_User")
    public String getAddUser(Model model){
        model.addAttribute("User", new User());
        return "addUser";
    }

    @PostMapping("/add_User")
    public String postAddUser(@ModelAttribute User user){
        if(userrepo.existsByUserEmail(user.getuserEmail())) {
            // Return some indication that the email already exists,
            // you can choose to return an error message or handle it as you prefer
            return "/add_User";
        }

        else{ userrepo.save(user);
            return "success";   }

    }

    @PostMapping("/userLogin")
    public ModelAndView userLogin(@RequestParam String userEmail, @RequestParam String userPassword) {
        User user = userrepo.findByUserEmail(userEmail);
        if (user != null && user.getuserPassword().equals(userPassword)) {
            // User login successful
            // Redirect or return some response
            session.setAttribute("loggedInUser", user);
            return new ModelAndView("redirect:/UserPage");
        } else {
            // User login failed
            // Redirect back to login page with error message
            ModelAndView modelAndView = new ModelAndView("main_page");
            modelAndView.addObject("userLoginError", "Invalid email or password");
            return modelAndView;
        }
    }
    @GetMapping("/logout")
    public String getMethodName() {
       session.removeAttribute("loggedInUser");
        return "/Home/Home.html";
    }
    
    

    @GetMapping("/UserPage")
    public String getUserPage() {

        return "UserPage";
    }

    @GetMapping("/getUserData")
    @ResponseBody
    public Map<String, String> getUserData() {
        Map<String, String> response = new HashMap<>();
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            response.put("userEmail", user.getuserEmail());
        } else {
            response.put("error", "User not logged in");
        }
        return response;
    }








    @GetMapping("/Plan")
    public String getPlan() {
        return "Plan";
    }




    @GetMapping("/Plan_One")
    public String purchasePlan1(Model model, @ModelAttribute Plan Plan) {
        model.addAttribute("UserandPlan", new UserandPlan());
        return "sixMonthSilver";
    }

    @GetMapping("/Plan_Two")
    public String purchasePlan2(Model model, @ModelAttribute Plan Plan) {
        model.addAttribute("UserandPlan", new UserandPlan());
        return "twelMonthSilver";
    }
    @GetMapping("/Plan_Three")
    public String purchasePlan3(Model model, @ModelAttribute Plan Plan) {
        model.addAttribute("UserandPlan", new UserandPlan());
        return "sixMonthGold";
    }
    @GetMapping("/Plan_Four")
    public String purchasePlan4(Model model, @ModelAttribute Plan Plan) {
        model.addAttribute("UserandPlan", new UserandPlan());
        return "twelMonthGold";
    }
    @GetMapping("/Plan_Five")
    public String purchasePlan5(Model model, @ModelAttribute Plan Plan) {
        model.addAttribute("UserandPlan", new UserandPlan());
        return "sixMonthDiamond";
    }
    @GetMapping("/Plan_Six")
    public String purchasePlan6(Model model, @ModelAttribute Plan Plan) {
        model.addAttribute("UserandPlan", new UserandPlan());
        return "twelMonthDiamond";
    }











    @PostMapping("/Plan_One")
    public String postMethodName1(@ModelAttribute UserandPlan userandplan) {
        Plan plan1 = (Plan) PlanRepo.findByPlanId(1);
        User user1 = (User) session.getAttribute("loggedInUser");
        UserandPlan purchase1 = new UserandPlan(0,LocalDateTime.now(), user1, plan1);
        if(UserAndPlanRepo.existsByUser(user1)){
            return "planAlreadyExists";
        }
        else{
            UserAndPlanRepo.save(purchase1);

            return "success_forPlan";}
    }

    @PostMapping("/Plan_Two")
    public String postMethodName2(@ModelAttribute UserandPlan userandplan) {
        Plan plan1 = (Plan) PlanRepo.findByPlanId(2);
        User user1 = (User) session.getAttribute("loggedInUser");
        UserandPlan purchase1 = new UserandPlan(0, LocalDateTime.now(), user1, plan1);
        if(UserAndPlanRepo.existsByUser(user1)){
            return "planAlreadyExists";
        }
        else{
            UserAndPlanRepo.save(purchase1);

            return "success_forPlan";}
    }

    @PostMapping("/Plan_Three")
    public String postMethodName3(@ModelAttribute UserandPlan userandplan) {
        Plan plan1 = (Plan) PlanRepo.findByPlanId(3);
        User user1 = (User) session.getAttribute("loggedInUser");
        UserandPlan purchase1 = new UserandPlan(0, LocalDateTime.now(), user1, plan1);
        if(UserAndPlanRepo.existsByUser(user1)){
            return "planAlreadyExists";
        }
        else{
            UserAndPlanRepo.save(purchase1);

            return "success_forPlan";}
    }
    @PostMapping("/Plan_Four")
    public String postMethodName4(@ModelAttribute UserandPlan userandplan) {
        Plan plan1 = (Plan) PlanRepo.findByPlanId(4);
        User user1 = (User) session.getAttribute("loggedInUser");
        UserandPlan purchase1 = new UserandPlan(0, LocalDateTime.now(), user1, plan1);
        if(UserAndPlanRepo.existsByUser(user1)){
            return "planAlreadyExists";
        }
        else{
            UserAndPlanRepo.save(purchase1);

            return "success_forPlan";}
    }

    @PostMapping("/Plan_Five")
    public String postMethodName5(@ModelAttribute UserandPlan userandplan) {
        Plan plan1 = (Plan) PlanRepo.findByPlanId(5);
        User user1 = (User) session.getAttribute("loggedInUser");
        UserandPlan purchase1 = new UserandPlan(0,LocalDateTime.now(), user1, plan1);
        if(UserAndPlanRepo.existsByUser(user1)){
            return "planAlreadyExists";
        }
        else{
            UserAndPlanRepo.save(purchase1);

            return "success_forPlan";}
    }
    @PostMapping("/Plan_Six")
    public String postMethodName6(@ModelAttribute UserandPlan userandplan) {
        Plan plan1 = (Plan) PlanRepo.findByPlanId(6);
        User user1 = (User) session.getAttribute("loggedInUser");
        UserandPlan purchase1 = new UserandPlan(0, LocalDateTime.now(), user1, plan1);
        if(UserAndPlanRepo.existsByUser(user1)){
            return "planAlreadyExists";
        }
        else{
            UserAndPlanRepo.save(purchase1);

            return "success_forPlan";}
    }




    @GetMapping("/BookAppointment")
    public String bookAppointment() {
        User user = (User) session.getAttribute("loggedInUser");

        // Check if the user exists in UserAndPlanRepo
        UserandPlan userAndPlan = UserAndPlanRepo.findByUser(user);
        if (userAndPlan != null) {

            return "bookAppointment";
        } else {

            return "haveToPurchasePlan";
        }
    }

    @GetMapping("/appointment")
    public String appointment() {
        User user = (User) session.getAttribute("loggedInUser");

        // Check if the user exists in UserAndPlanRepo
        UserandPlan userAndPlan = UserAndPlanRepo.findByUser(user);
        if(user == null){
            return "Main_page";
        }
        if (userAndPlan != null) {

            return "customer/appointment.html";
        } else {

            return "haveToPurchasePlan";
        }
    }

















}