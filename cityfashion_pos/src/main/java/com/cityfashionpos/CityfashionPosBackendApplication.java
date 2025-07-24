package com.cityfashionpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.cityfashionpos.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = "com.cityfashionpos.entity")
@ComponentScan(basePackages = "com.cityfashionpos")
@SpringBootApplication
public class CityfashionPosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityfashionPosBackendApplication.class, args);
	}

}
