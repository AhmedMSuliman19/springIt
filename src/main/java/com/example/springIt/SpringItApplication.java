package com.example.springIt;

import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringItApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringItApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringItApplication.class, args);
	}

	@Bean
	PrettyTime prettyTime() {
		return new PrettyTime();
	}

}
