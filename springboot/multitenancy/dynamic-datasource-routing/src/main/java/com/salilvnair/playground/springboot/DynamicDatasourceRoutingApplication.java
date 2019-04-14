package com.salilvnair.playground.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DynamicDatasourceRoutingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicDatasourceRoutingApplication.class, args);
	}

}
