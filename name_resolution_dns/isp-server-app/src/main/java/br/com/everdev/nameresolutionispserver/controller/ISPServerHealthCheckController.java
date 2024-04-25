package br.com.everdev.nameresolutionispserver.controller;

import br.com.everdev.nameresolutionispserver.service.ISPServerService;
import br.com.everdev.nameresolutionispserver.util.Constants;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class ISPServerHealthCheckController {

    @Autowired
    private ISPServerService ispServerService;

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
    public String callDNS() throws Exception {

        try{

            var JSONresponse = webClient.get()
                    .uri(Constants.DNSEndPoint.GET_REGISTERED_APPLICATIONS)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            JsonElement element = JsonParser.parseString(JSONresponse.toString());
            if(element.isJsonObject()){
                checkPorts(String.valueOf(element));
            }
            return JSONresponse.toString();


        } catch (Exception ex){
            throw new Exception("Não foi possivel realizar a requisição");
        }

    }

    public void checkPorts(String jsonInput) {
        ispServerService.fetchHealthStatuses(jsonInput)
                .subscribe(
                        response -> System.out.println("Health Check Response: " + response),
                        error -> System.err.println("Error during health check: " + error),
                        () -> System.out.println("Completed health checks.")
                );
    }

}
