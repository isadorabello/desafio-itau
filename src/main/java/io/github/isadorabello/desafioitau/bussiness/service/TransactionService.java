package io.github.isadorabello.desafioitau.bussiness.service;

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

        log.info("Iniciado o processamento de gravar transações");

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.info("Data e Hora no futuro");
            throw new UnprocessableEntity("Data e Hora no futuro");
        }
        if(dto.valor() < 0){
            log.info("Valor negativo");
            throw new UnprocessableEntity("Valor negativo");
        }

        listaTransactions.add(dto);
    }

    public void cleanTransactions(){
        listaTransactions.clear();
    }

    public List<TransactionRequestDTO> searchStatiscs(Integer segundos){
        // tempo atual - minutos(segundos) = intervalo
        OffsetDateTime intervalo = OffsetDateTime.now().minusSeconds(segundos);
        return listaTransactions.stream().filter(t -> t.dataHora().isAfter(intervalo)).toList();
    }

}
