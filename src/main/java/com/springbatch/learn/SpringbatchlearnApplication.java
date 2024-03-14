package com.springbatch.learn;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan("com.springbatch")
@EnableAsync
@EnableScheduling
public class SpringbatchlearnApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbatchlearnApplication.class, args);
	}

}
