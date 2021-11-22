package com.soft.ressystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.soft.ressystem" })
@EntityScan("ccom.soft.ressystem")
public class RessystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RessystemApplication.class, args);

	}

	@Bean
	public CommandLineRunner reservationSystem() {
		return (args) -> {

		};
	}
}