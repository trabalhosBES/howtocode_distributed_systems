package br.com.everdev.nameresolutiondnsserver.controller;


import br.com.everdev.nameresolutiondnsserver.util.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class DNSServerHealthCheckController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.service-discovery-app}")
    private String applicationDiscoveryURI;

    private final WebClient webClient;

    public DNSServerHealthCheckController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8761").build();
    }

    @GetMapping("/health")
    public String healthy() {
        return "Sou o DNS Server e estou online: " + LocalDateTime.now();
    }

    @GetMapping("get-registered-applications")
    public Mono<String> callDNS() {

        try{

            var response = webClient.get()
                    .uri(new URI(applicationDiscoveryURI))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .retrieve()
                    .bodyToMono(String.class);
            return response;


        } catch (Exception ex){
            return Mono.error(new Exception("Não foi possivel realizar a requisição"));
        }

    }

//    @GetMapping("/getRegisteredApplications")
//    public String getRegisteredApplications() {
//
//        try {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI(applicationDiscoveryURI))
//                    .GET()
//                    .header("Content-Type", "application/json")
//                    .header("Accept", "application/json")
//                    .build();
//            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
//            return response.body().toString();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}
