package com.axmayn;

import com.axmayn.customer.Customer;
import com.axmayn.customer.CustomerReposiory;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }

    @Bean
    CommandLineRunner runner(CustomerReposiory customerReposiory)
    {
        Faker faker = new Faker();

        Random random = new Random();
        Integer age = random.nextInt(16, 50);

        Customer aman = new Customer(faker.name().name(), faker.internet().safeEmailAddress(), age);

        return args -> {
            customerReposiory.save(aman);
        };

    }

}
