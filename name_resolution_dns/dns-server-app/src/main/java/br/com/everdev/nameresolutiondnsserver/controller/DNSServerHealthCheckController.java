package br.com.everdev.nameresolutiondnsserver.controller;


import br.com.everdev.nameresolutiondnsserver.util.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class DNSServerHealthCheckController {

    @Value("${spring.application.name}")
    private String appName;

    private final WebClient webClient;

    public DNSServerHealthCheckController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8761").build();
    }

    @GetMapping("/health")
    public String healthy() {
        return "Sou o DNS Server e estou online: " + LocalDateTime.now();
    }

    @GetMapping("call-eureka-get-applications")
    public Mono<ResponseEntity> callDNS() {

        try{

            return webClient.get()
                    .uri(Constants.EurekaEndPoint.LIST_APPLICATIONS)
                    .retrieve()
                    .bodyToMono(ResponseEntity.class);


        } catch (Exception ex){
            return Mono.error(new Exception("Não foi possivel realizar a requisição"));
        }

    }
}
