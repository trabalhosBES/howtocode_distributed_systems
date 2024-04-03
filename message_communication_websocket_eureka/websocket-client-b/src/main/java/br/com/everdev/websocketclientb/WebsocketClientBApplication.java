package br.com.everdev.websocketclientb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebsocketClientBApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketClientBApplication.class, args);
	}

}
