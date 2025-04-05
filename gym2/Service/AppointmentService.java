package com.gym1.gym1.Service;



import com.gym1.gym1.Model.Appointment;

import java.util.List;

public interface AppointmentService {
    // 查看所有预约
    List<Appointment> getAllAppointments();

    // 根据用户名查看预约详情
    List<Appointment> getAppointmentByuserId(Integer id);

    // 创建预约
    Appointment createAppointment(Appointment appointment);

//    Optional<Appointment> getAppointmentByemail(String email);

    // 删除预约
    void deleteAppointment(int id);

    // 更新预约
    void updateAppointment(Appointment appointment);




    List<Appointment> getAppointmentsByid(Integer id);
}

