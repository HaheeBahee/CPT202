package com.gym1.gym1.Service;

import com.gym1.gym1.Model.Trainer;
import com.gym1.gym1.Repository.trainerrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    private trainerrepo trainerRepo;

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepo.findAll();
    }

    @Override
    public Optional<Trainer> getTrainersByid(Integer id) {
        return trainerRepo.findByTrainerId(id);
    }

    @Override
    public List<Trainer> getTrainersByrating(Integer rating) {
        return trainerRepo.findByTrainerRanking(rating);
    }

    @Override
    public Trainer getTrainerByid(Integer id) {
       return trainerRepo.getTrainerByid(id);
    }
}
