package jpademo.controller;

import jakarta.annotation.Resource;
import jpademo.pojo.entity.JpaUser;
import jpademo.service.impl.JpaUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class JpaUserController {
    @Resource
    private JpaUserService jpaUserService;

    /**
     * 新增用户
     */
    @PostMapping("")
    public JpaUser addUser(@RequestBody JpaUser user){
        return jpaUserService.insertUser(user);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        jpaUserService.deleteUser(id);
    }

    /**
     * 修改用户
     */
    @PutMapping("")
    public JpaUser updateUser(@RequestBody JpaUser user){
        return jpaUserService.updateUser(user);
    }

    /**
     * 全查用户
     */
    @GetMapping("")
    public List<JpaUser> findAll(){
        return jpaUserService.findAllUser();
    }

    /**
     * id查用户
     */
    @GetMapping("/{id}")
    public JpaUser findbyId(@PathVariable("id") Long id){
        return jpaUserService.findUserById(id);
    }


}

