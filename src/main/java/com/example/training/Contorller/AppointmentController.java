package com.example.training.Contorller;

import com.example.training.entity.Appointment;
import com.example.training.repository.AppointmentRepository;
import com.example.training.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    // 查看所有预约
    @GetMapping("/view")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/views/{username}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUsername(@PathVariable("username") String username) {
        List<Appointment> appointments = appointmentService.getAppointmentsByUsername(username);
        return ResponseEntity.ok(appointments);
    }

    // 根据用户名查看预约详情
    @GetMapping("/{username}")
    public ResponseEntity<Optional<Appointment>> getAppointmentByUsername(@PathVariable("username") String username) {
        Optional<Appointment> appointment = appointmentService.getAppointmentByUsername(username);
        return ResponseEntity.ok(appointment);
    }

    // 预约
    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(createdAppointment);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointmentDTO) {
        // 检查是否存在该预约信息
        Optional<Appointment> existingAppointment = appointmentRepository.findById(appointmentDTO.getId());
        if (existingAppointment.isPresent()) {
            // 存在该预约信息，调用服务层方法更新预约信息
            appointmentService.updateAppointment(appointmentDTO);
            return ResponseEntity.ok("预约信息已成功更新");
        } else {
            // 不存在该预约信息，返回错误响应
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到待更新的预约信息");
        }
    }

    // 删除预约
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable("id") long id) {


        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

}