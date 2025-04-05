package com.group37.project_b.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group37.project_b.model.Shopmanager;



public interface shopManagerrepo extends JpaRepository<Shopmanager, Integer> {
    Shopmanager findByShopManagerId(int shopManagerId);
    boolean existsByShopManagerId(int shopManagerId);

}
