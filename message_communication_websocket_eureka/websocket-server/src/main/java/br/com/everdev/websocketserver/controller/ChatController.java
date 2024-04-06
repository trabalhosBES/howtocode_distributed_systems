package br.com.everdev.websocketserver.controller;

import br.com.everdev.websocketserver.domain.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ChatController {

    @MessageMapping("/healthCheck")
    @SendTo("/topic/messages")
    public Message healthCheck() {
        return Message.builder().message("I'm alive!").build();
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(String destination, String message) {
        return new Message(destination, message);
    }
}
