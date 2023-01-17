package com.linux.repository;

import com.linux.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AlienDbManager extends JpaRepository<Alien, Integer> {

    List<Alien> findByTech(String tech);

    @Query("FROM Alien WHERE tech = ?1 ORDER BY name DESC")
    List<Alien> findByTechSorted(String tech);
}
