package com.register.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.register.customer.model.TrainerRegister;

@Repository
public interface TrainerRegisterRepo extends JpaRepository<TrainerRegister, String> {
    TrainerRegister findByEmail(String email);
}