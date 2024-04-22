package br.com.everdev.rmiclientb.controller;

import br.com.everdev.rmiclientb.service.rmi.HealthCheckRemoteImpl;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class RMIClientBHealthCheckController {
//    @Autowired
//    @Lazy
//    private EurekaClient eurekaClient;

    @Autowired
    private HealthCheckRemoteImpl   healthCheckRemote;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/health")
    public String healthy() {
        return "Estpu vivo e bem! Sou a app "+appName+" - " + LocalDateTime.now();
    }

    @GetMapping("/remoteHealthCheck")
    public String remoteHealthCheck() {
        try {
            return healthCheckRemote.healthCheck();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping("/discover")
//    public String discover() {
//        Applications otherApps = eurekaClient.getApplications();
//        return otherApps.getRegisteredApplications().toString();
//    }
//
//    @PostMapping("/receiveCall/{name}")
//    public String receiveCall(@PathVariable String name, @RequestBody String message) {
//        return  message + "\nOlá " + name + ". Aqui é "+appName+" e recebi sua mensagem.";
//    }
//
//    @GetMapping("/makeCall/{name}")
//    public String makeCall(@PathVariable String name) throws URISyntaxException {
//        String message = "Olá, tem alguem ai??";
//
//        List<InstanceInfo> instances = eurekaClient.getInstancesById(name);
//
//        InstanceInfo instance = instances.getFirst();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI("http://"+instance.getIPAddr() + ":" + instance.getPort()+"/receiveCall/"+appName))
//                .POST(HttpRequest.BodyPublishers.ofString(message))
//                .build();
//        try {
//            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
//            return response.body().toString();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
