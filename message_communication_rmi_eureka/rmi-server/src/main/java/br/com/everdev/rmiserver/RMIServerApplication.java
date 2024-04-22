package br.com.everdev.rmiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RMIServerApplication {

	public static void main(String[] args) {
		System.setProperty("java.rmi.server.hostname","192.168.0.105");
		SpringApplication.run(RMIServerApplication.class, args);
	}

}
