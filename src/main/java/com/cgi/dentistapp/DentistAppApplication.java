package com.cgi.dentistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@SpringBootApplication
@RestController
public class DentistAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(DentistAppApplication.class, args);
    }
}
