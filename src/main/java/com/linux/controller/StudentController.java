package com.linux.controller;

import com.linux.repository.StudentDBManager;
import com.linux.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ComponentScan("com")
@EntityScan("com")
@SpringBootApplication(scanBasePackages={"com"})
public class StudentController {
    @Autowired
    StudentDBManager studentDBManager;

    @RequestMapping(value = "/getAllStudents", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentDBManager.findAll();
    }

    @RequestMapping(value = "/addStudent")
    public void addStudent(Student student) {
        System.out.println(student);
        studentDBManager.save(student);
    }

    @RequestMapping(value = "/getStudentFromName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> getStudentFromName(@PathVariable("firstName") String firstName) {
        System.out.println(firstName);

        return studentDBManager.findByStudentSorted(firstName);
    }
    @RequestMapping(value = "/getStudentFromName2/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> getStudentFromName2(@PathVariable("firstName") String firstName) {
        System.out.println(firstName);

        return studentDBManager.findByStudentSorted(firstName);
    }
}
