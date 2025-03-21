package io.github.isadorabello.desafioitau.business.service;

import io.github.isadorabello.desafioitau.controller.dtos.StatisticResponseDTO;
import io.github.isadorabello.desafioitau.controller.dtos.TransactionRequestDTO;
import io.github.isadorabello.desafioitau.infrastructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transacaoService;

    TransactionRequestDTO transacao;

    StatisticResponseDTO estatisticas;

    @BeforeEach
    void setUp(){
        transacao = new TransactionRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new StatisticResponseDTO(1l, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void deveAdicionarTransacaoComSucesso(){

        transacaoService.addTransactions(transacao);

        List<TransactionRequestDTO> transacoes = transacaoService.searchStatiscs(5000);

        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void deveLancarExcecaoCasoValorSejaNegativo(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.addTransactions(new TransactionRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals("Valor negativo", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoCasoDataOuHoraMaiorQueAtual(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.addTransactions(new TransactionRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Data e Hora no futuro", exception.getMessage());
    }

    @Test
    void deveLimparTransacaoComSucesso(){

        transacaoService.clearTransactions();

        List<TransactionRequestDTO> transacoes = transacaoService.searchStatiscs(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarTransacaoDentroDoIntervalo(){

        TransactionRequestDTO dto = new TransactionRequestDTO(10.00, OffsetDateTime.now().minusHours(1));

        transacaoService.addTransactions(transacao);
        transacaoService.addTransactions(dto);

        List<TransactionRequestDTO> transacoes = transacaoService.searchStatiscs(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));
    }

}
