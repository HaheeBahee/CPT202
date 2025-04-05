package com.example.training.repository;


import com.example.training.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT u FROM Customer u WHERE u.username = ?1")
    Optional<Customer> findByUsername(String username);
}





