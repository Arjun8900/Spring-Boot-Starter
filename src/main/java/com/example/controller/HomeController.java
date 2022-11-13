package com.example.controller;

import com.data.Alien;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
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
