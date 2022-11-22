package com.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Component
@Entity
//@Scope(value = "prototype")
@Table(name = "Alien")
public class Alien {

    @Id
    private int id;
    private String name;
//    private String language;
    private String tech;

//    @Autowired
//    @Qualifier("l1")
//    private Laptop laptop;
//    public Alien() {
//        System.out.println("Object created");
//    }
//
//    public void printHello() {
//        System.out.println("Hello " + laptop.getData());
//    }

}

