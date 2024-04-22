package br.com.everdev.rmiserver.service.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HealthCheckRemote extends Remote {
    String healthCheck() throws RemoteException;
}
