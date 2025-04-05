package com.register.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.register.customer.model.CustomerRegister;
import com.register.customer.model.TrainerRegister;
import com.register.customer.repository.CustomerRegisterRepo;
import com.register.customer.repository.TrainerRegisterRepo;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private TrainerRegisterRepo trainerRegisterRepo;
    @Autowired
    private CustomerRegisterRepo customerRegisterRepo;

    @GetMapping("/login")
    public String getlogin() {
        return "login";
    }

    @PostMapping("/customerLogin")
    public String customerLogin(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        CustomerRegister customerRegiser = customerRegisterRepo.findByEmail(email);

        if (customerRegiser != null && customerRegiser.getPassword().equals(password)) {
            // 성공적으로 로그인 처리
            session.setAttribute("customerEmail", customerRegiser.getEmail());
            session.setAttribute("userType", "customer");
            return "main"; // 성공시 리다이렉트
        } else {
            // 실패시 에러 메시지와 함께 로그인 페이지로 다시 리다이렉트
            model.addAttribute("loginError", "Invalid email or password.");
            return "login"; // 로그인 뷰 페이지로 리다이렉트
        }
    }

    @PostMapping("/trainerLogin")
    public String trainerLogin(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        TrainerRegister trainerRegister = trainerRegisterRepo.findByEmail(email);

        if (trainerRegister != null && trainerRegister.getPassword().equals(password)) {
            // 성공적으로 로그인 처리
            session.setAttribute("trainerEmail", trainerRegister.getEmail());
            session.setAttribute("userType", "trainer"); // Identify user as trainer

            return "main"; // 성공시 리다이렉트
        } else {
            // 실패시 에러 메시지와 함께 로그인 페이지로 다시 리다이렉트
            model.addAttribute("loginError", "Invalid email or password.");
            return "login"; // 로그인 뷰 페이지로 리다이렉트
        }
    }
}