package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	String weatherType(){
		return "weather type:";
	}

	@Bean
	String otherWeatherType(){
		return "other weather type:";
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplateBuilder().build();
	}
}
