package com.soft.ressystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com" })
@EntityScan(basePackages = { "com" })
@EnableJpaRepositories(basePackages = { "com" })
public class RessystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RessystemApplication.class, args);

	}

	private WeatherInfo weather = new WeatherInfo();

	@Bean
	public CommandLineRunner bookDemo() {
		return (args) -> {

			weather.getWeather();

		};
	}
}