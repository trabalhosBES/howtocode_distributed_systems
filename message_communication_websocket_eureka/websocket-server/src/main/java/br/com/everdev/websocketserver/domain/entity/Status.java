package br.com.everdev.websocketserver.domain.entity;

import lombok.Data;

public enum Status {
    NAO_ENVIADA("Não enviada"),
    ENVIANDO(""),
    ERRO_ENVIO(""),
    ENVIADA_COM_SUCESSO("");

    private String statusMessage;

    Status(String status) {
        this.statusMessage = status;
    }
}
