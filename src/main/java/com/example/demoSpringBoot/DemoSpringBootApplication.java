package com.example.demoSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.example.demoSpringBoot.rest.repository")
@ComponentScan("com.example.demoSpringBoot")
public class DemoSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}
}

