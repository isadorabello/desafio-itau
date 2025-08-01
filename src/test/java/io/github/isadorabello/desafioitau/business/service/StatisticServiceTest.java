package io.github.isadorabello.desafioitau.business.service;

import io.github.isadorabello.desafioitau.controller.dtos.StatisticResponseDTO;
import io.github.isadorabello.desafioitau.controller.dtos.TransactionRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceTest {

    @InjectMocks
    StatisticService estatisticasService;

    @Mock
    TransactionService transacaoService;

    TransactionRequestDTO transacao;

    StatisticResponseDTO estatisticas;

    @BeforeEach
    void setUp(){
        transacao = new TransactionRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new StatisticResponseDTO(1l, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void calcularEstatisticasComSucesso(){
        when(transacaoService.searchTransactions(60))
                .thenReturn(Collections.singletonList(transacao));

        StatisticResponseDTO resultado = estatisticasService.transactionsStats(60);

        verify(transacaoService, times(1)).searchTransactions(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticas);
    }

    @Test
    void calcularEstatisticasQuandoListaVazia(){

        StatisticResponseDTO estasticaEsperado =
                new StatisticResponseDTO(0l, 0.0, 0.0, 0.0, 0.0);

        when(transacaoService.searchTransactions(60))
                .thenReturn(Collections.emptyList());

        StatisticResponseDTO resultado = estatisticasService.transactionsStats(60);

        verify(transacaoService, times(1)).searchTransactions(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estasticaEsperado);
    }

}
