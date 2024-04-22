package br.com.everdev.rmiclientb.service.rmi;

import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@Service
public class HealthCheckRemoteImpl {
    static HealthCheckRemote remoteHealthCheck = null;
    static String remoteHealthCheckMessage = "blank";
    public String healthCheck() throws MalformedURLException, NotBoundException, RemoteException {
        remoteHealthCheck = (HealthCheckRemote) Naming.lookup("//"+"192.168.0.105:8761"+"/healthCheck");

        remoteHealthCheckMessage = remoteHealthCheck.healthCheck();

        return remoteHealthCheckMessage;
    }
}
