package com.mes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MESServiceApplication {

	/*static {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,
				System.getenv("PROFILE") != null ? System.getenv("PROFILE") : "local");
	}*/

	public static void main(String[] args) {
		SpringApplication.run(MESServiceApplication.class, args);
	}

}
