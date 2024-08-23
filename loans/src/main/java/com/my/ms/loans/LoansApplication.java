package com.my.ms.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@RestController
@OpenAPIDefinition(info =  @Info(
		title = "Loans microservice REST API Documentation",
		description = "EazyBank Loans microservice REST API Documentation",
		version = "V1",
		contact = @Contact(
				name = "Mehaboob Shaik",
				email = "MehaboobShaik@gmail.com",
				url = "You can enter any URL for references"),
		license = @License(
				name = "Apache 2.0",
				url = "You can enter any URL for references")
		),
		externalDocs = @ExternalDocumentation(
				description =  "EazyBank Loans microservice REST API Documentation",
				url = "You can enter any URL for references")
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
	
	@Operation(summary = "Hello world from Loans Microservice")
	@ApiResponse(responseCode = "200", description = "Hello World")
	@GetMapping("/")
	public String sayHello() {
		return "Hello world from Loans Microservice";
	}
}
