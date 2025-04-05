package com.gym1.gym1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gym1.gym1.Model.Trainer;

import java.util.List;
import java.util.Optional;


@Repository
public interface trainerrepo extends JpaRepository<Trainer, Integer>{
    Trainer findByTrainerEmail(String trainerEmail);
    
    boolean existsByTrainerEmail(String trainerEmail);

//    @Query("SELECT t.trainerId FROM Trainer t WHERE t.trainerEmail = ?1")
//    Optional<Trainer> findByTrainerId(int trainerId);

    @Query("SELECT t FROM Trainer t WHERE t.trainerRanking = ?1")
    List<Trainer> findByTrainerRanking(Integer rating);
    @Query("SELECT t FROM Trainer t WHERE t.trainerId = ?1")
    Trainer getTrainerByid(int id);
    @Query("SELECT t FROM Trainer t")
    List<Trainer> findAll();


}
