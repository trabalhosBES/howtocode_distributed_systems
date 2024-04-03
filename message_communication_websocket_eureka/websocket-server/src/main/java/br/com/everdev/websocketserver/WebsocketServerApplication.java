package br.com.everdev.websocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WebsocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketServerApplication.class, args);
	}

}
