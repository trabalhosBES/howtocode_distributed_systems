package br.com.everdev.rmiclientb.service.rmi;

import java.rmi.Remote;

public interface HealthCheckRemote extends Remote {
    String healthCheck();
}
