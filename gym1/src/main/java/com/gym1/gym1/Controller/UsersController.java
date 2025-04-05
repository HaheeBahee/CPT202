package com.gym1.gym1.Controller;

import com.gym1.gym1.Model.User;
import com.gym1.gym1.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UsersController {

    // 假设这是模拟的用户数据


    // 构造函数中初始化一些用户数据
  @Autowired
  private userRepo userRepository;

    @GetMapping("/getUserIdByEmail/{email}")
    public ResponseEntity<?> getUserIdByEmail(@PathVariable String email) {
        Integer userId = userRepository.getUserIdByEmail(email);
        return ResponseEntity.ok(userId);
    }
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userRepository.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }




}

