package com.example.jwt_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
//@SpringBootApplication
public class JwtV1Application {

	public static void main(String[] args) {
		SpringApplication.run(JwtV1Application.class, args);
	}

}
