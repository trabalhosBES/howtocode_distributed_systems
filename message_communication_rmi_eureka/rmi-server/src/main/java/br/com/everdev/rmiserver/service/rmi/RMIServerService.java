package br.com.everdev.rmiserver.service.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

@Component
public class RMIServerService extends UnicastRemoteObject implements HealthCheckRemote {
    public RMIServerService() throws RemoteException {
        super();
    }

    public String healthCheck() {
        System.out.println("Chamada RMI recebida1");
        return "Estou vivo! São " + LocalDateTime.now();
    }

//    @Bean
    static void init() {
        try {
            RMIServerService server = new RMIServerService();
            Registry registry = LocateRegistry.createRegistry(1010);
//            Naming.rebind("HealthCheck", server);
            registry.bind("healthCheck", server);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
