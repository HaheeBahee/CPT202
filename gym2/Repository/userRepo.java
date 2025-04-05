package com.gym1.gym1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gym1.gym1.Model.User;


@Repository
public interface userRepo extends JpaRepository<User, Integer> {
    User findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);

//    @Query("SELECT u FROM User u WHERE u.userEmail = ?1")
//    User findUSerByemail(String userEmail);
    @Query("SELECT u.userId FROM User u WHERE u.userEmail = ?1")
    int getUserIdByEmail(String userEmail);
    @Query("SELECT u FROM User u WHERE u.userEmail = ?1")
    User getUserByEmail(String email);
}
