package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindUpaWithLowerCapacity {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Mono<Upa> findUpaWithLowerCapacity(Integer state) {
        return upaRepositoryGateway.findUpaWithLowerCapacity(state);
    }
}
