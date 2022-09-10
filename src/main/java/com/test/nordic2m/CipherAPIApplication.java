package com.test.nordic2m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class CipherAPIApplication {
	/**
	 * SpringApplication -Entry point to start API services
	 * @param args
	 */
	public static void main(String[] args) 
	{
		SpringApplication.run(CipherAPIApplication.class, args);
	}
}
