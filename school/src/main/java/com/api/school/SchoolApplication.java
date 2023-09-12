package com.api.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // serve para gerar as dependecias quando for necessarias
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);

	}

	@GetMapping("/")
	public String Index() {
		return ("Olá Mundoooooo");
	}

}
