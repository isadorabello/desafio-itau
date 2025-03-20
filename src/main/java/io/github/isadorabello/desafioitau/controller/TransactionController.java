package io.github.isadorabello.desafioitau.controller;

import io.github.isadorabello.desafioitau.bussiness.service.TransactionService;
import io.github.isadorabello.desafioitau.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<Void> addTransactionEP(@RequestBody TransactionRequestDTO dto){
        service.addTransactions(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactionEP(){
        service.clearTransactions();
        return ResponseEntity.ok().build();
    }
}
