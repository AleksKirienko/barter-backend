package ru.sibsutis.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class BarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarterApplication.class, args);
    }

}
