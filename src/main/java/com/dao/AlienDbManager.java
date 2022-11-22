package com.dao;

import com.data.Alien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
public interface AlienDbManager extends CrudRepository<Alien, Integer> {

}
