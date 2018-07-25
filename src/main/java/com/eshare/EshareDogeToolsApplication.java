package com.eshare;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EshareDogeToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshareDogeToolsApplication.class, args);
	}
}
