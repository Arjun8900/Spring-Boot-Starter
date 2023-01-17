package com.linux.controller;

import com.linux.model.Alien;
import com.linux.repository.AlienDbManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@ComponentScan("com")
@EntityScan("com")
@SpringBootApplication(scanBasePackages = {"com"})
public class RestController {

    @Autowired
    AlienDbManager alienDbManager;

    @RequestMapping("/getAlien/{id}")
    @ResponseBody
    public Optional<Alien> getAlien(@PathVariable("id") int id) {
        return alienDbManager.findById(id);
    }

    @RequestMapping(value = "/getAliens/{tech}", produces = "application/json")
    @ResponseBody
    public List<Alien> getAliens(@PathVariable("tech") String tech) {
        System.out.println(tech);
        return alienDbManager.findByTechSorted(tech);
    }

    @RequestMapping("/getAllAliens")
    @ResponseBody
    public List<Alien> getAllAliens() {
        return alienDbManager.findAll();
    }

    @RequestMapping("/addAlien")
    @ResponseBody
    public void addAlien(Alien alien) {

        alienDbManager.save(alien);
    }
}
