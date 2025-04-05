package jpademo.service.impl;

import jakarta.annotation.Resource;
import jpademo.pojo.entity.JpaUser;
import jpademo.repository.JpaUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaUserServiceImpl implements JpaUserService {
    @Resource
    private JpaUserRepository jpaUserRepository;

    @Override
    public JpaUser insertUser(JpaUser user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public JpaUser updateUser(JpaUser user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public List<JpaUser> findAllUser() {
        return jpaUserRepository.findAll();
    }

    @Override
    public JpaUser findUserById(Long id) {
        return jpaUserRepository.findById(id).orElse(null);
    }
}

