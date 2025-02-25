package br.com.example.upafacil.ms_agendamento.application.usecases;

import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindUpaWithLowerCapacity {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Mono<Upa> findUpaWithLowerCapacity(Integer state) {
        return upaRepositoryGateway.findUpaWithLowerCapacity(state);
    }
}
