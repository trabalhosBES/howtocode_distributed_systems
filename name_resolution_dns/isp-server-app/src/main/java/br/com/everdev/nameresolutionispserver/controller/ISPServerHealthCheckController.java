package br.com.everdev.nameresolutionispserver.controller;

import br.com.everdev.nameresolutionispserver.util.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class ISPServerHealthCheckController {

    @Value("${spring.application.name}")
    private String appName;

    private final WebClient webClient;

    public ISPServerHealthCheckController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    @GetMapping("/health")
    public String healthy() {
        return "Sou o ISP Server e estou online!" + LocalDateTime.now();
    }

    @GetMapping("/service")
    public Mono<ResponseEntity> callDNS() {

        try{

            return webClient.get()
                    .uri(Constants.DNSEndPoint.CALL_EUREKA_GET_APPLICATIONS)
                    .retrieve()
                    .bodyToMono(ResponseEntity.class);


        } catch (Exception ex){
            return Mono.error(new Exception("Não foi possivel realizar a requisição"));
        }

    }

}
