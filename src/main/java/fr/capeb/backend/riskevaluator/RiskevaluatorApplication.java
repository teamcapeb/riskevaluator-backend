package fr.capeb.backend.riskevaluator;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class RiskevaluatorApplication {
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(RiskevaluatorApplication.class, args);
		
	}
}

