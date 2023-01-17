package com.linux.controller;

import com.linux.model.Alien;
import com.linux.dto.LinuxResponse;
import com.linux.service.AgentInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//@Controller
@RestController
@ComponentScan("com")
@EntityScan("com")
@SpringBootApplication(scanBasePackages = {"com"})
@RequestMapping(value = "/agent", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class LinuxController {
    private final AgentInformation agentInformation = new AgentInformation();
//    @RequestMapping(value = "/linux", method = RequestMethod.GET)
    @GetMapping("/com/linux")
    public ModelAndView linux(Alien alien) {
        log.info("Entered in /linux");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("com/linux");
        return modelAndView;
    }

//    @RequestMapping(value = "/linuxAgent", method = RequestMethod.GET)
//    public ModelAndView home(String name) throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        RestartAgent restartAgent = new RestartAgent();
//        List<String> directories = restartAgent.getLatestRemoteDirCmd(name);
//        modelAndView.setViewName("showAgent");
//        modelAndView.addObject("directories", directories);
//
////        System.out.println("Home access by name = " + alien);
//        return modelAndView;
//    }

    @GetMapping(value = "/files/{podName}/{packageName}")
    @ResponseBody
    public List<LinuxResponse> linuxFiles(@PathVariable("podName") String podName,
                                          @PathVariable("packageName") String packageName) {
        List<LinuxResponse> linuxFiles = agentInformation.getLatestRemoteDirCmd(podName, packageName);
        log.info(String.format("Files size = %s, are = %s", linuxFiles.size(), linuxFiles));
        return linuxFiles;
    }

    @GetMapping(value = "/status/{name}")
    @ResponseBody
    public List<LinuxResponse> agentStatus(@PathVariable("name") String name) throws Exception {
        List<LinuxResponse> linuxResponse = agentInformation.getAgentStatus(name);
        log.info(String.format("Files size = %s, are = %s", linuxResponse.size(), linuxResponse));
        return linuxResponse;
    }

    @GetMapping(value = "/shutdown/{name}")
    @ResponseBody
    public List<LinuxResponse> shutdownAgent(@PathVariable("name") String name) {
        List<LinuxResponse> linuxResponse = agentInformation.shutDownAgent(name);
        log.info(String.format("Files size = %s, are = %s", linuxResponse.size(), linuxResponse));
        return linuxResponse;
    }

    @GetMapping(value = "/start/{name}")
    @ResponseBody
    public List<LinuxResponse> startAgent(@PathVariable("name") String name) {
        List<LinuxResponse> linuxResponse = agentInformation.startUpAgent(name);
        log.info(String.format("Files size = %s, are = %s", linuxResponse.size(), linuxResponse));
        return linuxResponse;
    }

    @GetMapping(value = "/restart/{name}")
    @ResponseBody
    public List<LinuxResponse> restartAgent(@PathVariable("name") String name) {
        List<LinuxResponse> linuxResponse = agentInformation.restartAgent(name);
        log.info(String.format("Files size = %s, are = %s", linuxResponse.size(), linuxResponse));
        return linuxResponse;
    }

//    @RequestMapping(value = "/linux2", method = RequestMethod.GET)
    @GetMapping("/linux2")
    public String linux2(String name)  {
        log.info("Inside linux2");
        return "Working";
    }
}
