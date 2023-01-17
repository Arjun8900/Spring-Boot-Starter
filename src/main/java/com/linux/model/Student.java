package com.linux.model;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Component
@Entity
@Table(name = "Student")
public class Student {
    @Id
    private int id;
    private int rollNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
}
