package com.group37.project_b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group37.project_b.model.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    
}
