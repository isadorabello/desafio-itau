package io.github.isadorabello.desafioitau.simple.dto;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class TransacaoRequest {

    @NotNull
    private double valor;

    @NotNull
    private OffsetDateTime dataHora;

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
