package com.gym1.gym1.Service;

import com.gym1.gym1.Model.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {

    List<Trainer> getAllTrainers();

    Optional<Trainer> getTrainersByid(Integer id);

    List<Trainer> getTrainersByrating(Integer ranking);

    Trainer getTrainerByid(Integer id);
}
