//package com.example.training.service;
//
//import com.example.training.entity.Customer;
//import com.example.training.repository.UserRepository;
//import jakarta.annotation.Resource;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomerServiceImpl implements CustomerService{
//
//    @Resource
//    private UserRepository userRepository;
//    @Override
//    public Customer addcustomer(Customer customer) {
//        return userRepository.save(customer);
//    }
//
//    @Override
//    public void deletecustomer(int id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    public Customer changepassword(Customer customer) {
//        // 从传入的 Customer 对象中获取用户的唯一标识符和新密码
//        int customerId = customer.getId();
//        String newPassword = customer.getPassword();
//
//        // 根据用户的唯一标识符从数据库中获取用户信息
//        Customer existingCustomer = userRepository.findById(customerId).orElse(null);
//
//        // 如果找到了用户信息，则更新用户密码并保存
//        if (existingCustomer != null) {
//            existingCustomer.setPassword(newPassword);
//            return userRepository.save(existingCustomer);
//        }
//
//        // 如果未找到用户信息，则返回 null，或者根据需求进行其他处理
//        return null;
//    }
//
//    @Override
//    public Customer changeemail(Customer customer) {
//        // 从传入的 Customer 对象中获取用户的唯一标识符和新密码
//        int customerId = customer.getId();
//        String newEmail = customer.getEmail();
//
//        // 根据用户的唯一标识符从数据库中获取用户信息
//        Customer existingCustomer = userRepository.findById(customerId).orElse(null);
//
//        // 如果找到了用户信息，则更新用户密码并保存
//        if (existingCustomer != null) {
//            existingCustomer.setEmail(newEmail);
//            return userRepository.save(existingCustomer);
//        }
//
//        // 如果未找到用户信息，则返回 null，或者根据需求进行其他处理
//        return null;
//}
//    }
