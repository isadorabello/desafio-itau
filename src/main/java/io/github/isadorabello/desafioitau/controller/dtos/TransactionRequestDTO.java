package io.github.isadorabello.desafioitau.controller.dtos;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double valor, OffsetDateTime dataHora) {
}
