package com.tang.missu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tang")
public class MissuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MissuApplication.class, args);
    }

}
