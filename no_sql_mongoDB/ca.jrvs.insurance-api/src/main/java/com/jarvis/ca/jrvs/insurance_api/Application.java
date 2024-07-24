package com.jarvis.ca.jrvs.insurance_api;

import com.jarvis.ca.jrvs.insurance_api.model.Address;
import com.jarvis.ca.jrvs.insurance_api.model.Person;
import com.jarvis.ca.jrvs.insurance_api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class Application {


    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PersonService personService) {
        return runner -> {
        };
    }

}
