package com.kaynaak.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"webapp"})
public class RestApplication  {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
