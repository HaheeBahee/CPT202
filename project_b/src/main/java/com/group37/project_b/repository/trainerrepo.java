package com.group37.project_b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group37.project_b.model.Trainer;


@Repository
public interface trainerrepo extends JpaRepository<Trainer, Integer>{
    Trainer findByTrainerId(int trainerId);
    boolean existsByTrainerId(int trainerId);

}
