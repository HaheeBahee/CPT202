package com.group37.project_b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group37.project_b.model.User;




@Repository
public interface userRepo extends JpaRepository<User, Integer> {
    User findByUserEmail(String userEmail);
    User findByUserId(int userId);
    boolean existsByUserId(int userId);
    boolean existsByUserEmail(String userEmail);
}
