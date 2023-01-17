package com.linux.controller;

import com.linux.model.Alien;
import com.linux.repository.AlienDbManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ComponentScan("com")
@EntityScan("com")
@SpringBootApplication(scanBasePackages={"com"})
@Slf4j
public class HomeController {

    @Autowired
    AlienDbManager alienDbManager;

    @RequestMapping("/")
    public String home() {
        log.info("Inside root");
        return "home";
    }

    // This comes from home.jsp
    @RequestMapping("/addAlienOld")
    public ModelAndView addAlien(Alien alien) {
        log.info("Adding alien: " + alien);
        System.out.println(alien);
        alienDbManager.save(alien);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("alien", alien.toString());
        modelAndView.setViewName("display");
        return modelAndView;
    }

    @RequestMapping("/getAlienById")
    public ModelAndView getAlien(@RequestParam  int id) {
        log.info("Adding alien: " + id);
        Alien dbAlien = alienDbManager.findById(id).orElse(new Alien());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("alien", dbAlien.toString());
        modelAndView.setViewName("showAlien");
        return modelAndView;
    }

    @RequestMapping("/getAliensFromTech")
    public ModelAndView getAliensFromTech(@RequestParam String tech) {
        System.out.println(tech);
        List<Alien> aliens = alienDbManager.findByTechSorted(tech);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("aliens", aliens);
        modelAndView.setViewName("showAliens");
        return modelAndView;
    }

    @RequestMapping("home")
    public ModelAndView home(Alien alien) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("obj", alien);
        modelAndView.setViewName("home");
        System.out.println("Home access by name = " + alien);
        return modelAndView;
    }

    @RequestMapping("single")
    public ModelAndView homeSingleObject(@RequestParam("name") String myName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", myName);
        modelAndView.setViewName("display");
        System.out.println("Home access by name = " + myName);
        return modelAndView;
    }

    @RequestMapping("servlet")
    public String oldHome(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        System.out.println("Home access by name = " + name);
        session.setAttribute("name", name);
        return "display";
    }
}
