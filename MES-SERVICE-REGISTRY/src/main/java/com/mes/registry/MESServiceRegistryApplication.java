package com.mes.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MESServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MESServiceRegistryApplication.class, args);
	}

}
