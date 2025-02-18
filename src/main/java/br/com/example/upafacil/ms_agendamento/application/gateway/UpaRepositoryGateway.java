package br.com.example.upafacil.ms_agendamento.application.gateway;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UpaRepositoryGateway {

    Mono<Upa> createUpa(Upa upa);

    Mono<Upa> findUpaById(Long upaId);

    Flux<Upa> findAllUpa();

    Mono<Void> deleteUpaById(Long upaId);

    Mono<Upa> updateUpa(Upa upa);
}
