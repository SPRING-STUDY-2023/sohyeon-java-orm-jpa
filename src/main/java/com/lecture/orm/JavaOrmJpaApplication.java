package com.lecture.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaOrmJpaApplication {

    public static void main(String[] args) {

        SpringApplication.run(JavaOrmJpaApplication.class, args);

    }

}
