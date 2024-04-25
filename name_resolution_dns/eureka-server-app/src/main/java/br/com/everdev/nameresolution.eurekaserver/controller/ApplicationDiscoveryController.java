package br.com.everdev.nameresolution.eurekaserver.controller;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.Applications;
import org.glassfish.jaxb.core.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ApplicationDiscoveryController {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/health")
    public String healthy() {

        return "Sou o Eureka Server e estou online!" + LocalDateTime.now();
    }

    @GetMapping("/list-applications")
    public ResponseEntity listApplications() throws Exception {
        try{
            ///TODO: Remover comentários quando o professor resolver o getApplications
            //Applications otherApps = eurekaClient.getApplications();
            //return otherApps.getRegisteredApplications().toString();
            return ResponseEntity.ok().build();
        } catch (Exception exception){
            throw new Exception();
        }

    }

}
