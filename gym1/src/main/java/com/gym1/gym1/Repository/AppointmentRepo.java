package com.gym1.gym1.Repository;

import com.gym1.gym1.Model.Appointment;
import com.gym1.gym1.Model.Trainer;
import com.gym1.gym1.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    @Query("SELECT a FROM Appointment a WHERE a.id = ?1")
    List<Appointment> findAppointmentByid(Integer id);

    @Query("SELECT a FROM Appointment a WHERE a.user.userId = ?1")
    List<Appointment> findAppointmentByuserid(Integer id);


//    @Query("SELECT a FROM Appointment a WHERE a.user =:user AND a.appointmentTime > :start AND a.appointmentTime< :end AND a.appointmentEndTime > :start AND a.appointmentEndTime< :end")
//    List<Appointment> findByUserAndAppointmentTimeBetween(User user, LocalDateTime start, LocalDateTime end);
//
//    @Query("SELECT a FROM Appointment a WHERE a.user = :user AND :time > a.appointmentTime AND  :time < a.appointmentEndTime")
//    List<Appointment> findByUserAndAppointmentTimeBetween(User user, LocalDateTime time);
//
//    @Query("SELECT a FROM Appointment a WHERE a.trainer = :trainer AND a.appointmentTime > :start And  a.appointmentTime < :end AND a.appointmentEndTime > :start AND a.appointmentEndTime < :end")
//    List<Appointment> findByTrainerAndAppointmentTimeBetween(Trainer trainer, LocalDateTime start, LocalDateTime end);
//
//
//    @Query("SELECT a FROM Appointment a WHERE a.trainer = :trainer AND :time > a.appointmentTime AND :time< a.appointmentEndTime")
//    List<Appointment> findByTrainerAndAppointmentTimeBetween(Trainer trainer, LocalDateTime time);
//
//    @Query("SELECT a from Appointment a where a.user= :user and a.appointmentTime between :start and :end")
//    List<Appointment> findByUserAppointmentTimesame(User user,LocalDateTime start, LocalDateTime end);



//
//    @Query("SELECT a FROM Appointment a WHERE a.user =:user AND a.appointmentTime BETWEEN :start AND :end AND Date_ADD(a.appointmentTime,Interval(a.duration Minute) BETWEEN :start AND :end")
//    List<Appointment> findByUserAndAppointmentTimeBetween(User user, LocalDateTime start, LocalDateTime end);

//    @Query("SELECT a FROM Appointment a WHERE a.user = :user AND :time between a.appointmentTime AND Date_ADD(a.appointmentTime,Interval a.duration Minute)")
//    List<Appointment> findByUserAndAppointmentTimeBetween(User user, LocalDateTime time);
//
//    @Query("SELECT a FROM Appointment a WHERE a.trainer = :trainer AND a.appointmentTime BETWEEN :start AND :end AND Date_ADD(a.appointmentTime,Interval a.duration Minute) BETWEEN :start AND :end")
//    List<Appointment> findByTrainerAndAppointmentTimeBetween(Trainer trainer, LocalDateTime start, LocalDateTime end);
//
//
//    @Query("SELECT a FROM Appointment a WHERE a.trainer = :trainer AND :time between a.appointmentTime AND Date_ADD(a.appointmentTime,Interval a.duration Minute)")
//    List<Appointment> findByTrainerAndAppointmentTimeBetween(Trainer trainer, LocalDateTime time);
//
//    @Query("SELECT a from Appointment a where a.user= :user and a.appointmentTime between :start and :end")
//    List<Appointment> findByUserAppointmentTimesame(User user,LocalDateTime start, LocalDateTime end);


    @Query("SELECT a FROM Appointment a WHERE a.trainer.trainerId = ?1")
    List<Appointment> findappointmentBytrainerid(Integer id);


    @Query("SELECT a.appointmentTime, a.duration FROM Appointment a where a.user = :user")
    List<Object[]> getAllLocalTimeAndDuration(User user);

    @Query("SELECT a.appointmentTime, a.duration FROM Appointment a where a.trainer = :trainer")
    List<Object[]> getAllLocalTimeAndDuration(Trainer trainer);

    @Query("SELECT a FROM Appointment a WHERE a.id = ?1")
    Appointment getAppointmtbyappointmentid(Integer id);
}
