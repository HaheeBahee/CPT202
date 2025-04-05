package com.gym1.gym1.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym1.gym1.Model.Trainer;
import com.gym1.gym1.Model.User;
import com.gym1.gym1.Repository.trainerrepo;
import com.gym1.gym1.Repository.userRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegisterController {
    @Autowired
    private trainerrepo trainerRepo;
    @Autowired
    private userRepo userRepo;

    @GetMapping("/userType")
    public String getUserType() {
        return "userType";
    }

    @PostMapping("/userType")
    public String postUserType(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String userType = request.getParameter("userType");
        HttpSession session = request.getSession();
        session.setAttribute("userType", userType);
        if ("trainer".equals(userType)) {
            if (userType == null) {
                return "redirect:/userType";
            } else {
                request.getSession().setAttribute("userType", userType);
                return "agreement";
            }
        } else if ("customer".equals(userType)) {
            if (userType == null) {
                return "redirect:/userType";
            } else {
                request.getSession().setAttribute("userType", userType);
                return "agreement";
            }
        } else {
            // Handle unknown userType by redirecting back to the selection page
            redirectAttributes.addFlashAttribute("error", "Please select a valid user type.");
            return "redirect:/userType";
        }
    }

    @PostMapping("/submit_agreement")
    public String postAgreement(HttpServletRequest request, HttpSession session,
            RedirectAttributes redirectAttributes) {
        String userType = (String) session.getAttribute("userType");
        boolean termsAgreed = request.getParameter("term1") != null && request.getParameter("term2") != null;
        redirectAttributes.addAttribute("userType", userType); // Adding userType as a query parameter

        if (!termsAgreed) {
            redirectAttributes.addFlashAttribute("error", "You must agree to all the terms to proceed.");
            return "redirect:/" + userType + "Agreement";
        }
        if ("trainer".equals(userType)) {
            session.setAttribute("termsAgreed", termsAgreed);
            return "accountCreationTrainer";
        } else if ("customer".equals(userType)) {
            return "accountCreation";
        } else {
            // Handle unknown userType by redirecting back to the selection page
            redirectAttributes.addFlashAttribute("error", "Please select a valid user type.");
            return "redirect:/userType";
        }

    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");
        String userType = (String) session.getAttribute("userType");
        Map<String, String> response = new HashMap<>();

        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            response.put("status", "invalid");
            return ResponseEntity.badRequest().body(response);
        }

        boolean isUsed = false;
        if ("trainer".equals(userType)) {
            Trainer trainer = trainerRepo.findByTrainerEmail(email);
            isUsed = trainer != null;
        } else if ("customer".equals(userType)) {
            User customer = userRepo.findByUserEmail(email);
            isUsed = customer != null;
        }

        if (isUsed) {
            response.put("status", "used");
        } else {
            response.put("status", "available");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/accountCreation")
    public String accountCreationCustomer(HttpServletRequest request,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String emailValidationStatus = request.getParameter("emailValidationStatus");
        String userName = request.getParameter("userName");
        int userAge = Integer.parseInt(request.getParameter("userAge"));

        if ("available".equals(emailValidationStatus)) {
            User user = new User();
            user.setuserEmail(email);
            user.setuserPassword(password);
            user.setuserName(userName);
            user.setuserAge(userAge);
            userRepo.save(user);
            return "completion";
        } else {
            redirectAttributes.addFlashAttribute("error", "Email is not available.");
            return "redirect:/accountCreation";
        }
    }

    @PostMapping("/accountCreationTrainer")
    public String accountCreationTrainer(HttpServletRequest request,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("trainerName");
        String ranking = request.getParameter("trainerRanking");
        String introduction = request.getParameter("trainerIntroduction");
        String emailValidationStatus = request.getParameter("emailValidationStatus");

        if ("available".equals(emailValidationStatus)) {
            Trainer trainer = new Trainer();
            trainer.settrainerEmail(email);
            trainer.settrainerPassword(password);
            trainer.settrainerName(name);
            trainer.settrainerRanking(Integer.parseInt(ranking));
            trainer.settrainerIntroduction(introduction);
            trainerRepo.save(trainer);
            return "completion"; // 혹은 성공 페이지로 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("error", "Email is not available.");
            return "redirect:/accountCreationTrainer";
        }
    }
}


