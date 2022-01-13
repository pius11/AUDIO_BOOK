package com.dimata.demo.audiobook.demo_audio_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class DemoDataSiswaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDataSiswaApplication.class, args);
	}

	// @Bean
	// public ModelMapper modelMapper(){
	// 	return new ModelMapper();
	// }
}
