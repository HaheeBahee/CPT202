package com.example.training.repository;

import com.example.training.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("SELECT a FROM Appointment a WHERE a.username = ?1")
    Optional<Appointment> findByUsername(String username);
    @Query("SELECT a FROM Appointment a WHERE a.username = ?1")
    List<Appointment> findAppointmentByUsername(String username);
//
//    @Query("SELECT a FROM Appointment a WHERE a.time = ?1")
//    Optional<Appointment> findByTime(Date time);
//
//    @Query("SELECT a FROM Appointment a WHERE a.date = ?1")
//    Optional<Appointment> findByDate(Date date);
//
//    @Query("SELECT a FROM Appointment a WHERE a.trainer = ?1")
//    Optional<Appointment> findByTrainer(String trainer);
    @Query("SELECT a FROM Appointment a WHERE a.username = :username AND a.date = :date AND a.time = :time AND a.trainer = :trainer")
    Optional<Appointment> findByUsernameAndDateAndTimeAndTrainer(String username, String date, String time, String trainer);
 @Query("SELECT a FROM Appointment a WHERE a.id = ?1")
    Optional<Appointment> findById(Long id);
}
