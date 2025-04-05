package com.register.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.register.customer.model.PayingCustomer;

@Repository
public interface PayingCustomerRepo extends JpaRepository<PayingCustomer, String> {
    PayingCustomer findByEmail(String email);
}
