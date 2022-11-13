package com.data;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("l1")
public class Laptop {
    private int id;
    private String brand;

    public String getData() {
        return "Laptop Info = " + this.toString();
    }
}
