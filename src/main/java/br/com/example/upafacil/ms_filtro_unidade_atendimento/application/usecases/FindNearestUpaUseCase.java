package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindNearestUpaUseCase {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Flux<Upa> findNearestUpa(Double userLatitude, Double userLongitude) {
        return  upaRepositoryGateway.findNearestUpa(userLatitude, userLongitude);
    }


}
