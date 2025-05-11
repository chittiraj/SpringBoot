package com.dl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "SpringBoot CRM Application REST API Documentations",
				version = "v1.1.1",
				contact = @Contact(
						name = "Chitti Raja",
						email = "chitti@gmail.com",
						url =""
						),
				license = @License(
						name = "Tomcat 2.0",
						url = ""
						)
				)
		)

@SpringBootApplication
public class SpringBootCrmValidationsDocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrmValidationsDocsApplication.class, args);
	}

}
