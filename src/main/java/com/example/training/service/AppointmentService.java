package com.example.training.service;

import com.example.training.entity.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    // 查看所有预约
    List<Appointment> getAllAppointments();

    // 根据用户名查看预约详情
    Optional<Appointment> getAppointmentByUsername(String username);

    // 创建预约
    Appointment createAppointment(Appointment appointment);


    // 删除预约
    void deleteAppointment(long id);

    // 更新预约
    void updateAppointment(Appointment appointment);


    List<Appointment> getAppointmentsByUsername(String username);
}

