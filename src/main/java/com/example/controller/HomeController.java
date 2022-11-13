package com.example.controller;

import com.data.Alien;
import com.dao.AlienDbManager;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HomeController {

    @Autowired
    AlienDbManager alienDbManager;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    // This comes from home.jsp
    @RequestMapping("/addAlien")
    public String addAlien(Alien alien) {
        return "home";
    }

    @RequestMapping("home")
    public ModelAndView home(Alien alien) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("obj", alien);
        modelAndView.setViewName("home");
        System.out.println("Home access by name = " + alien);
        return modelAndView;
    }

    public ModelAndView homeSingleObject(@RequestParam("name") String myName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", myName);
        modelAndView.setViewName("home");
        System.out.println("Home access by name = " + myName);
        return modelAndView;
    }

    public String oldHome(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        System.out.println("Home access by name = " + name);
        session.setAttribute("name", name);
        return "home";
    }
}
