package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class SpringReactorDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactorDemo2Application.class, args);
	}
}