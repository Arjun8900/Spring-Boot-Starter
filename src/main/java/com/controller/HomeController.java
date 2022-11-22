package com.controller;

import com.dao.AlienDbManager;
import com.data.Alien;
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

@Controller
@ComponentScan("com")
@EntityScan("com")
@SpringBootApplication(scanBasePackages={"com"})
public class HomeController {

    @Autowired
    AlienDbManager alienDbManager;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    // This comes from home.jsp
    @RequestMapping("/addAlien")
    public ModelAndView addAlien(Alien alien) {
        System.out.println(alien);
        alienDbManager.save(alien);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("alien", alien.toString());
        modelAndView.setViewName("display");
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
