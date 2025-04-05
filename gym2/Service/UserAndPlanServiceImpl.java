package com.gym1.gym1.Service;

import com.gym1.gym1.Repository.userAndplanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAndPlanServiceImpl implements UserAndPlanService{
    @Autowired
   private userAndplanRepo userAndplanRepo;

    @Override
    public Integer getidFromid(Integer id) {
     return  userAndplanRepo.geidfromid(id);

    }
}
