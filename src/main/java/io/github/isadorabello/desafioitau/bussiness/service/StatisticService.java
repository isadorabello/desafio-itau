package io.github.isadorabello.desafioitau.bussiness.service;


import io.github.isadorabello.desafioitau.controller.dtos.StatisticResponseDTO;
import io.github.isadorabello.desafioitau.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    public final TransactionService tService;

    public StatisticResponseDTO transactionsStats (Integer busca){
        List<TransactionRequestDTO> transactions = tService.searchStatiscs(busca);
        DoubleSummaryStatistics stats = transactions.stream().mapToDouble(TransactionRequestDTO::valor).summaryStatistics();
        return new StatisticResponseDTO(stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
    }

}
