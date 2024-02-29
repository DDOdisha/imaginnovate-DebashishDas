package com.imaginnovate.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class EmployeeTaxCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeTaxCalculatorApplication.class, args);
	}

}
