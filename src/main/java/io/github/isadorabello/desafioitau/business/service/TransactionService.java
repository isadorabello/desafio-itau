package io.github.isadorabello.desafioitau.business.service;

import io.github.isadorabello.desafioitau.controller.dtos.TransactionRequestDTO;
import io.github.isadorabello.desafioitau.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final List<TransactionRequestDTO> listaTransactions = new ArrayList<>();

    public void addTransactions(TransactionRequestDTO dto){

        log.info("Iniciado o processamento de gravar transações {}", dto);

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e Hora no futuro");
            throw new UnprocessableEntity("Data e Hora no futuro");
        }
        if(dto.valor() < 0){
            log.error("Valor negativo");
            throw new UnprocessableEntity("Valor negativo");
        }

        listaTransactions.add(dto);
        log.info("Transações adicionadas - OK");
    }

    public void clearTransactions(){
        log.info("Iniciado o processamento de deletar transações deletadas");
        listaTransactions.clear();
        log.info("Transações deletadas - OK");
    }

    public List<TransactionRequestDTO> searchTransactions(Integer busca){
        // tempo atual - minutos(segundos) = intervalo
        log.info("Inicadas buscas de transações por tempo {}", busca);
        OffsetDateTime intervalo = OffsetDateTime.now().minusSeconds(busca);

        log.info("Retorno de transações com sucesso");
        return listaTransactions.stream().filter(t -> t.dataHora().isAfter(intervalo)).toList();
    }

}
