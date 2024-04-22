package br.com.everdev.websocketserver.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class User {
    private UUID id;
    private String name;
    private String email;
}
