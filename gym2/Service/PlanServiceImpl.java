package com.gym1.gym1.Service;

import com.gym1.gym1.Repository.planRepo;
import com.gym1.gym1.Repository.userAndplanRepo;
import com.gym1.gym1.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService{
    @Autowired
    private planRepo planropo;
    @Autowired
    private userRepo userrepo;
    @Autowired
    private userAndplanRepo userandplanrepo;
    @Override
    public Integer getratingfromid(Integer id) {
        return planropo.getratingfromid(id);
    }

    @Override
    public Integer getratingfromemail(String email) {
        userrepo.getUserIdByEmail(email);
        userandplanrepo.geidfromid( userrepo.getUserIdByEmail(email))  ;
      return  planropo.getratingfromid(userandplanrepo.geidfromid( userrepo.getUserIdByEmail(email)));
    }
}
