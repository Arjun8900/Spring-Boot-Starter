package com.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Data
@Component
//@Scope(value = "prototype")
public class Alien {
    private int id;
    private String name;
    private String language;

    @Autowired
    @Qualifier("l1")
    private Laptop laptop;
    public Alien() {
        System.out.println("Object created");
    }

    public void printHello() {
        System.out.println("Hello " + laptop.getData());
    }
}

