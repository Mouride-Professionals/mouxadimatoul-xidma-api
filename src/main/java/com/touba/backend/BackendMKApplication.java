package com.touba.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BackendMKApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMKApplication.class, args);
	}

}
