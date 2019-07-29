package com.infosys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DirectoryEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectoryEurekaApplication.class, args);
	}

}
