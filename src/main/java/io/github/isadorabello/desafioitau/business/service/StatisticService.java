package io.github.isadorabello.desafioitau.business.service;


import io.github.isadorabello.desafioitau.controller.dtos.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {

    public final TransactionService tService;

    public StatisticResponseDTO transactionsStats (Integer busca){
        log.info("Busca de estatisticas pelo intervalo " + busca);

        long start = System.currentTimeMillis();

        List<TransactionRequestDTO> transactions = tService.searchTransactions(busca);

        if(transactions.isEmpty()){
            return new StatisticResponseDTO(0L,0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics stats = transactions.stream().mapToDouble(TransactionRequestDTO::valor).summaryStatistics();

        long finish = System.currentTimeMillis();

        System.out.println("Tempo de requisicao: " + (finish - start) + "ms");

        log.info("Retorno das estatisticas - OK");
        // log.info(String.valueOf(stats));
        return new StatisticResponseDTO(stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
    }

}
