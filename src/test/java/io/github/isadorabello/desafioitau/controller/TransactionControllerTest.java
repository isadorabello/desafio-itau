package io.github.isadorabello.desafioitau.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.isadorabello.desafioitau.business.service.TransactionService;
import io.github.isadorabello.desafioitau.controller.dtos.TransactionRequestDTO;
import io.github.isadorabello.desafioitau.infrastructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @InjectMocks
    TransactionController transacaoController;

    @Mock
    TransactionService transacaoService;

    TransactionRequestDTO transacao;

    MockMvc mockMvc;

    @Autowired
    final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup(){
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
        transacao = new TransactionRequestDTO(20.0, OffsetDateTime.of(2025, 2, 18, 14, 30, 0, 0, ZoneOffset.UTC));
    }

    @Test
    void deveAdicionarTransacaoComSucesso() throws Exception {

        doNothing().when(transacaoService).addTransactions(transacao);

        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveGerarExcecaoAoAdicionarTransacao() throws Exception {
        doThrow(new UnprocessableEntity("Erro de requisição")).when(transacaoService).addTransactions(transacao);
        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deveDeletarTransacoesComSucesso() throws Exception {
        doNothing().when(transacaoService).clearTransactions();
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }

}
