package br.com.everdev.nameresolution.validacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ValidacaoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ValidacaoApplication.class, args);
	}

}
