package io.github.isadorabello.desafioitau.controller;

import io.github.isadorabello.desafioitau.bussiness.service.StatisticService;
import io.github.isadorabello.desafioitau.controller.dtos.StatisticResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService service;

    public ResponseEntity<StatisticResponseDTO> searchStatistics(@RequestParam(value = "intervalo", required = false, defaultValue = "60") Integer busca){
        return ResponseEntity.ok(service.transactionsStats(busca));
    }
}
