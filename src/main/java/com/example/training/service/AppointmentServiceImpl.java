package com.example.training.service;

import com.example.training.entity.Appointment;
import com.example.training.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentByUsername(String username) {
        return appointmentRepository.findByUsername(username);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointment.ifPresent(value -> appointmentRepository.delete(value));
    }




    @Override
    public void updateAppointment(Appointment updatedAppointment) {
        // 通过唯一标识符（ID）获取待更新的预约信息
        Optional<Appointment> existingAppointment = appointmentRepository.findById(updatedAppointment.getId());
        existingAppointment.ifPresent(appointment -> {
            // 更新需要修改的字段
            appointment.setDate(updatedAppointment.getDate());
            appointment.setTime(updatedAppointment.getTime());
            appointment.setTrainer(updatedAppointment.getTrainer());
            // 保存更新后的预约信息
            appointmentRepository.save(appointment);
        });
        }


    @Override
    public List<Appointment> getAppointmentsByUsername(String username) {
        return appointmentRepository.findAppointmentByUsername(username);
    }
//    @Override
//    public void updateAppointment(Appointment appointment) {
//        Optional<Appointment> existingAppointment = appointmentRepository.findById(appointment.getId());
//        existingAppointment.ifPresent(a -> {
//            // 更新需要修改的字段
//            a.setDate(appointment.getDate());
//            a.setTime(appointment.getTime());
//            a.setTrainer(appointment.getTrainer());
//            // 保存更新后的预约信息
//            appointmentRepository.save(a);
//        });
}
