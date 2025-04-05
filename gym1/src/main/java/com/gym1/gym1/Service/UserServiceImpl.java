package com.gym1.gym1.Service;

import com.gym1.gym1.Model.Appointment;
import com.gym1.gym1.Repository.AppointmentRepo;
import com.gym1.gym1.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UseService {


@Autowired
    private AppointmentRepo appointmentRepository;
@Autowired
private userRepo userRepository;
    @Override
    public Integer getidfromemail(String email) {
        return userRepository.getUserIdByEmail(email);
    }


}
