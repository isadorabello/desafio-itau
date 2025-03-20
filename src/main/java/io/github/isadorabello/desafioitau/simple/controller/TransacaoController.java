package io.github.isadorabello.desafioitau.simple.controller;

import io.github.isadorabello.desafioitau.simple.dto.TransacaoRequest;
import io.github.isadorabello.desafioitau.simple.model.Transacao;
import io.github.isadorabello.desafioitau.simple.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criarTransacao(@Valid @RequestBody TransacaoRequest request){
        if(request.getDataHora().isAfter(OffsetDateTime.now()) || request.getValor() <= 0){
            return ResponseEntity.unprocessableEntity().build();
        }
        service.adicionarTransacao(new Transacao(request.getValor(), request.getDataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> limparTransacoes(){
        service.limparTransacao();
        return ResponseEntity.ok().build();
    }

}
