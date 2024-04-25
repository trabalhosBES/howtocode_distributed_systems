package br.com.everdev.nameresolutionispserver.service;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ISPServerService {

    @Autowired
    private Gson gson;
    private final WebClient webClient;

    public ISPServerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Flux<String> fetchHealthStatuses(String jsonInput) {
        Root root = gson.fromJson(jsonInput, Root.class);
        return Flux.fromArray(root.applications.application)
                .flatMap(application -> Flux.fromArray(application.instance))
                .flatMap(instance -> fetchHealthStatus(instance.getHealthCheckUrl()));
    }

    private Mono<String> fetchHealthStatus(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }

}

//JSON SERIALIZE
class Root {
    Applications applications;
}

class Applications {
    Application[] application;
}

class Application {
    Instance[] instance;
}

class Instance {
    String hostName;
    @SerializedName("healthCheckUrl")
    String healthCheckUrl;

    public String getHealthCheckUrl() {
        return healthCheckUrl.replace("/actuator/health", "/health");
    }
}
