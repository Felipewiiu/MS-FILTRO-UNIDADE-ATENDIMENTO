package br.com.example.upafacil.ms_agendamento.application.usecases;

import br.com.example.upafacil.ms_agendamento.application.exeptions.NotFoundUpaException;
import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReduceServiceCapacityUpa {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Mono<Upa> reduceServiceCapacityUpa(Long upaId){
        return upaRepositoryGateway.reduceServiceCapacityUpa(upaId)
                .switchIfEmpty(Mono.error(new NotFoundUpaException(upaId)));
    }
}
