package br.com.everdev.websocketserver.service.websocket;

import jakarta.websocket.OnMessage;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebsocketServer {
    @OnMessage
    public String healthCheck(String message) {
        return "Estou vivo e funcionando!";
    }
}
