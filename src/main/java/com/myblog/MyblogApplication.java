package com.myblog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyblogApplication.class, args);
		System.out.println("program is running");
	}

//CRATING THE BEAN SO THAT IOC MUST K NOW WHICH BEAN TO BE CREATED
	@Bean
    public ModelMapper getModelMapper() {

		return new ModelMapper();
	}
}
