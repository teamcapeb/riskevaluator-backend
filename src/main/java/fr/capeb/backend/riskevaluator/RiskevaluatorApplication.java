package fr.capeb.backend.riskevaluator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class RiskevaluatorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RiskevaluatorApplication.class, args);
	}
	
}

