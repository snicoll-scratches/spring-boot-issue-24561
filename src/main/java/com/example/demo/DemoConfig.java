package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

	@Bean
	public static DemoPostProcessor demoPostProcessor() {
		return new DemoPostProcessor();
	}

}
