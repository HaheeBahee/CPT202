package com.example.training.service;

import com.example.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 这里我默认填写了一个用户，实际中你可以使用参数 username 查询数据库，获取真实用户信息来返回
//        String encodedPassword = passwordEncoder.encode("123");
//        return User.builder().username("admin").password(encodedPassword).disabled(false)
//                .accountExpired(false).credentialsExpired(false).accountLocked(false).authorities("admin").build();
//    }
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    // Fetch user details from the database based on the provided username
  return  userRepository.findByUsername(username).orElseThrow();
//            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

//    // Build UserDetails object using the fetched user details
//    return org.springframework.security.core.userdetails.User.builder()
//            .username(user.getUsername())
//            .password(user.getPassword())
//            .build();
}
}
