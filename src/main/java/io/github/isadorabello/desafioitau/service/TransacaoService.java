package io.github.isadorabello.desafioitau.service;

import io.github.isadorabello.desafioitau.model.Transacao;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransacaoService {
    // não pode usar banco de dados -> usar memoria da aplicação -> usar uma collection (threadSafe) -> concurrent linked queue

    private final Queue<Transacao> transacoes = new ConcurrentLinkedQueue<>();
}
