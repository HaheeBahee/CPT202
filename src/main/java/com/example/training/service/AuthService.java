package com.example.training.service;

import com.example.training.dto.ReqRes;
import com.example.training.entity.Customer;
import com.example.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes reqRes) {
      ReqRes response = new ReqRes();
      try {
          Customer customer= new Customer();
            customer.setUsername(reqRes.getUsername());
            customer.setPassword(passwordEncoder.encode(reqRes.getPassword()));
            customer.setEmail(reqRes.getEmail());
            Customer result = userRepository.save(customer);
            if (result != null&& result.getId() >0){
              response.setCustomer(result);
                response.setMessage("User registration successful");
                response.setStatusCode(200);
            }
      }catch (Exception e){
          response.setStatusCode(500);
          response.setMessage(e.getMessage());
      }
        return response;
    }
    public ReqRes signIn(ReqRes reqRes){
        ReqRes response = new ReqRes();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqRes.getUsername(),reqRes.getPassword()));
            var user= userRepository.findByUsername(reqRes.getUsername()).orElseThrow();
            System.out.println("User is"+user);
            var jwt = jwtUtil.generateToken(user);
            var refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(),user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Login successful");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    public ReqRes refreshToken(ReqRes reqRes){
        ReqRes response = new ReqRes();
        String username=jwtUtil.extractUsername(reqRes.getToken());
       Customer user = userRepository.findByUsername(username).orElseThrow();
        if(jwtUtil.isTokenValid(reqRes.getToken(),user)) {
            var jwt = jwtUtil.generateToken(user);
            var refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(reqRes.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Token refreshed successfully");
        }
        return response;
    }
    public ReqRes logout(ReqRes reqRes) {
        ReqRes response = new ReqRes();
        try {
            // 获取当前登录用户的用户名
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            // 根据用户名查找用户
            Customer user = userRepository.findByUsername(username).orElseThrow();
            // 在这里执行你的注销逻辑，比如清除用户的会话信息等
            // 清除用户的会话信息通常是从数据库或缓存中删除用户的令牌信息等
            // 这里只是一个简单的示例，你需要根据你的具体需求实现注销逻辑
            // 这里只是将用户认证信息从 Spring Security 上下文中清除，实际应用中可能需要更复杂的操作
            SecurityContextHolder.clearContext();
            response.setStatusCode(200);
            response.setMessage("Logout successful");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Logout failed: " + e.getMessage());
        }
        return response;
    }
}

