//package com.gym1.gym1.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.gym1.gym1.Model.Customer;
//import com.gym1.gym1.Model.Trainer;
//import com.gym1.gym1.Repository.CustomerRepo;
//import com.gym1.gym1.Repository.trainerrepo;
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class LoginController {
//    @Autowired
//    private trainerrepo trainerRepo;
//    @Autowired
//    private CustomerRepo customerRepo;
//
//    @GetMapping("/login")
//    public String getlogin() {
//        return "login";
//    }
//
//    @PostMapping("/customerLogin")
//    public String customerLogin(
//            @RequestParam String email,
//            @RequestParam String password,
//            Model model,
//            HttpSession session) {
//
//        Customer customer = customerRepo.findByEmail(email);
//
//        if (customer != null && customer.getPassword().equals(password)) {
//            session.setAttribute("customerEmail", customer.getEmail());
//            session.setAttribute("userType", "customer");
//            session.setAttribute("userId", customer.getUserId());
//            return "main";
//        } else {
//            model.addAttribute("loginError", "Invalid email or password.");
//            return "login";
//        }
//    }
//
//    @PostMapping("/trainerLogin")
//    public String trainerLogin(
//            @RequestParam String email,
//            @RequestParam String password,
//            Model model,
//            HttpSession session) {
//
//        Trainer trainer = trainerRepo.findByTrainerEmail(email);
//
//        if (trainer != null && trainer.gettrainerPassword().equals(password)) {
//            // 성공적으로 로그인 처리
//            session.setAttribute("trainerEmail", trainer.gettrainerEmail());
//            session.setAttribute("userType", "trainer");
//            session.setAttribute("userId", trainer.gettrainerId());
//            return "main";
//        } else {
//            model.addAttribute("loginError", "Invalid email or password.");
//            return "login";
//        }
//    }
//}
