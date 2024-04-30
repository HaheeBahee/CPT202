package com.gym1.gym1.Repository;

import com.gym1.gym1.Model.Appointment;
import com.gym1.gym1.Model.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    @Query("SELECT a FROM Appointment a WHERE a.id = ?1")
    List<Appointment> findAppointmentByid(Integer id);
}
