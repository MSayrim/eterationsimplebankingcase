package com.example.eterationsimplebankingcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@Configuration
@SpringBootApplication
public class EterationsimplebankingcaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EterationsimplebankingcaseApplication.class, args);
    }

}
