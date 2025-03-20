package io.github.isadorabello.desafioitau.simple.service;

import io.github.isadorabello.desafioitau.simple.model.Transacao;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransacaoService {
    // não pode usar banco de dados -> usar memoria da aplicação -> usar uma collection (threadSafe) -> concurrent linked queue
    private final Queue<Transacao> transacoes = new ConcurrentLinkedQueue<>();

    public void adicionarTransacao( Transacao t){
        transacoes.add(t);
    }

    public void limparTransacao(){
        transacoes.clear();
    }

    public DoubleSummaryStatistics getEstatisticas(){
        OffsetDateTime agora = OffsetDateTime.now();
        return transacoes.stream().filter(t -> t.getDataHora().isAfter(agora.minusSeconds(60))).mapToDouble(Transacao::getValor).summaryStatistics();
    }
}
