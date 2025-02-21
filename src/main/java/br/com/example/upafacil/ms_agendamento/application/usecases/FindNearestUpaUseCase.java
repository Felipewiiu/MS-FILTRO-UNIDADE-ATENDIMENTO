package br.com.example.upafacil.ms_agendamento.application.usecases;

import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class FindNearestUpaUseCase {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Mono<Upa> findNearestUpa(Double userLatitude, Double userLongitude) {
        return  upaRepositoryGateway.findNearestUpa(userLatitude, userLongitude);
    }


}
