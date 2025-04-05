package com.gym1.gym1.Service;

import com.gym1.gym1.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UseService {


@Autowired
private userRepo userRepository;
    @Override
    public Integer getidfromemail(String email) {
        return userRepository.getUserIdByEmail(email);
    }


}
