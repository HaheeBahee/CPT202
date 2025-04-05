package com.gym1.gym1.Service;

import com.gym1.gym1.Model.Appointment;
import com.gym1.gym1.Repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepository;
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentByuserId(Integer id) {
        return appointmentRepository.findAppointmentByuserid(id);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }



    @Override
    public void deleteAppointment(int id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointment.ifPresent(value -> appointmentRepository.delete(value));
    }


    @Override
    public void updateAppointment(Appointment updatedAppointment) {

    }




    @Override
    public List<Appointment> getAppointmentsByid(Integer id) {
        return appointmentRepository.findAppointmentByid(id);
    }
}