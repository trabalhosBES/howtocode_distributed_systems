package br.com.everdev.rmiclienta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RMIClientAApplication {
	public static void main(String[] args) {
		SpringApplication.run(RMIClientAApplication.class, args);
	}

}
