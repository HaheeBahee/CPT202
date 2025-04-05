package com.register.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.register.customer.model.CustomerRegister;

@Repository
public interface CustomerRegisterRepo extends JpaRepository<CustomerRegister, String> {
    CustomerRegister findByEmail(String email);
}