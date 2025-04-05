package jpademo.repository;


import jpademo.pojo.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Classname JpaUserRepository
 * @Description TODO
 * @Date 2020/8/13 15:12
 * @Created by orange
 */
public interface JpaUserRepository extends JpaRepository<JpaUser, Long> {
}

