package com.register.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.register.customer.model.PaymentDetails;

@Repository
public interface PaymentDetailsRepo extends JpaRepository<PaymentDetails, String> {
    PaymentDetails findByEmail(String email);

}