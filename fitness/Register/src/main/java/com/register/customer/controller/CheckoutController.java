package com.register.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.register.customer.model.PayingCustomer;
import com.register.customer.model.PaymentDetails;
import com.register.customer.model.PurchasedPlan;
import com.register.customer.repository.PayingCustomerRepo;
import com.register.customer.repository.PaymentDetailsRepo;
import com.register.customer.repository.PurchasedPlanRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes({ "customerEmail", "userType" })
public class CheckoutController {
    @Autowired
    private PayingCustomerRepo payingCustomerRepo;
    @Autowired
    private PaymentDetailsRepo paymentDetailsRepo;
    @Autowired
    private PurchasedPlanRepo purchasedPlanRepo;

    @GetMapping("/checkout")
    public String checkout(
            @RequestParam(required = false) String plan,
            @RequestParam(required = false) String duration,
            @RequestParam(required = false) String price,
            Model model, HttpSession session) {

        String userType = (String) session.getAttribute("userType");
        String email = (String) session.getAttribute("customerEmail");

        if (email == null || userType == null) {
            return "redirect:/login";
        }
        if (!userType.equals("customer")) {
            model.addAttribute("trainerAlert", "Trainer cannot purchase the Fitness Plan.");
            return "fitnessPlan";
        }

        PurchasedPlan existingPlan = purchasedPlanRepo.findByEmail(email);
        model.addAttribute("alreadyPurchased", existingPlan != null);
        if (existingPlan != null) {
            model.addAttribute("purchaseAlert",
                    "You have already purchased a plan and cannot purchase another.");
            return "fitnessPlan";
        } else {
            model.addAttribute("showAlert", false); // 플랜이 없을 때는 알림을 표시하지 않음
        }

        PayingCustomer payingCustomer = payingCustomerRepo.findByEmail(email);
        if (payingCustomer == null) {
            payingCustomer = new PayingCustomer();
            payingCustomer.setEmail(email);
        }
        PaymentDetails paymentDetails = paymentDetailsRepo.findByEmail(email);
        if (paymentDetails == null) {
            paymentDetails = new PaymentDetails();
            paymentDetails.setEmail(email);
        }

        model.addAttribute("email", email);
        model.addAttribute("plan", plan);
        model.addAttribute("duration", duration);
        model.addAttribute("price", price);
        model.addAttribute("payingCustomer", payingCustomer);
        model.addAttribute("paymentDetails", paymentDetails);

        return "checkout"; // Show checkout page with plan details
    }

    @PostMapping("/processCheckout")
    public String processCheckout(@ModelAttribute PayingCustomer formPayingCustomer,
            @ModelAttribute PaymentDetails formPaymentDetails,
            HttpSession session, @RequestParam String plan,
            @RequestParam String duration,
            @RequestParam String price) {
        String email = (String) session.getAttribute("customerEmail");
        if (email == null) {
            return "redirect:/login"; // Ensure user is logged in
        }
        PurchasedPlan existingPlan = purchasedPlanRepo.findByEmail(email);
        if (existingPlan != null) {
            return "redirect:/fitnessPlan?error=alreadyPurchased"; // Redirect them with an error query param
        }

        PayingCustomer payingCustomer = payingCustomerRepo.findByEmail(email);
        if (payingCustomer == null) {
            payingCustomer = new PayingCustomer();
            payingCustomer.setEmail(email);
        }
        payingCustomer.setName(formPayingCustomer.getName());
        payingCustomer.setPhone(formPayingCustomer.getPhone());
        payingCustomer.setGender(formPayingCustomer.getGender());
        payingCustomerRepo.save(payingCustomer);

        PaymentDetails paymentDetails = paymentDetailsRepo.findByEmail(email);
        if (paymentDetails == null) {
            paymentDetails = new PaymentDetails();
            paymentDetails.setEmail(email);
        }
        paymentDetails.setCardholderName(formPaymentDetails.getCardholderName());
        paymentDetails.setCardNumber(formPaymentDetails.getCardNumber());
        paymentDetails.setExpiryMonth(formPaymentDetails.getExpiryMonth());
        paymentDetails.setExpiryYear(formPaymentDetails.getExpiryYear());
        paymentDetails.setCvc(formPaymentDetails.getCvc());
        paymentDetailsRepo.save(paymentDetails);

        PurchasedPlan purchasedPlan = new PurchasedPlan();
        purchasedPlan.setEmail(email);
        purchasedPlan.setPlanName(plan);
        purchasedPlan.setDuration(duration);
        purchasedPlan.setPrice(price);
        purchasedPlanRepo.save(purchasedPlan);

        return "fitnessPlan"; // Redirect after successful saving
    }

    @GetMapping("/fitnessPlan")
    public String showFitnessPlan(Model model, HttpSession session) {
        String email = (String) session.getAttribute("customerEmail");

        if (email == null) {
            return "redirect:/login";
        }
        return "fitnessPlan";
    }
}
