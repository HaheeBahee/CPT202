package com.register.customer.controller;

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

import com.register.customer.model.CustomerRegister;
import com.register.customer.model.TrainerRegister;
import com.register.customer.repository.CustomerRegisterRepo;
import com.register.customer.repository.TrainerRegisterRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegisterController {
    @Autowired
    private TrainerRegisterRepo trainerRegisterRepo;
    @Autowired
    private CustomerRegisterRepo customerRegisterRepo;

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
            return "accountCreation";
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

        // 정규 표현식을 사용하여 이메일 형식 검증
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            response.put("status", "invalid");
            return ResponseEntity.badRequest().body(response);
        }

        boolean isUsed = false;
        if ("trainer".equals(userType)) {
            isUsed = trainerRegisterRepo.existsById(email);
        } else if ("customer".equals(userType)) {
            isUsed = customerRegisterRepo.existsById(email);
        }

        if (isUsed) {
            response.put("status", "used");
        } else {
            response.put("status", "available");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/accountCreation")
    public String postAccountCreation(HttpServletRequest request,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        String userType = (String) session.getAttribute("userType");
        String email = request.getParameter("email"); // Assuming email is passed as a parameter
        String password = request.getParameter("password"); // Assuming password is also passed

        String emailValidationStatus = request.getParameter("emailValidationStatus");
        session.setAttribute("emailValidationStatus", emailValidationStatus);

        if ("trainer".equals(userType) && ("available".equals(emailValidationStatus))) {
            TrainerRegister trainer = new TrainerRegister();

            trainer.setEmail(email);
            trainer.setPassword(password);
            trainerRegisterRepo.save(trainer);
            return "completion";
        } else if ("customer".equals(userType) && ("available".equals(emailValidationStatus))) {
            CustomerRegister customer = new CustomerRegister();

            customer.setEmail(email);
            customer.setPassword(password);
            customerRegisterRepo.save(customer);
            return "completion";
        } else {
            redirectAttributes.addFlashAttribute("error", "Please select a valid user type.");
            return "redirect:/userType";
        }
    }
}
