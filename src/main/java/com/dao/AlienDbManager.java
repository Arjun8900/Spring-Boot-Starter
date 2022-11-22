package com.dao;

import com.data.Alien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface AlienDbManager extends CrudRepository<Alien, Integer> {

    List<Alien> findByTech(String tech);

    @Query("FROM Alien WHERE tech = ?1 ORDER BY name DESC")
    List<Alien> findByTechSorted(String tech);
}
