package io.github.isadorabello.desafioitau.controller;


import io.github.isadorabello.desafioitau.dto.EstatisticasResponse;
import io.github.isadorabello.desafioitau.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final TransacaoService service;

    public EstatisticasController(TransacaoService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<EstatisticasResponse> getEstatisticas(){
        DoubleSummaryStatistics statistics = service.getEstatisticas();
        return ResponseEntity.ok(new EstatisticasResponse(statistics));
    }

}
