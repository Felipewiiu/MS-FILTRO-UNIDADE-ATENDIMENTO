package br.com.example.upafacil.ms_agendamento.application.usecases;

import br.com.example.upafacil.ms_agendamento.application.exeptions.NotFoundUpaException;
import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeletUpaUseCase {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Mono<Void> deleteUpaById(Long upaId) {
        return upaRepositoryGateway.findUpaById(upaId)
                .switchIfEmpty(Mono.error(new NotFoundUpaException(upaId)))
                .then(upaRepositoryGateway.deleteUpaById(upaId));
    }
}
