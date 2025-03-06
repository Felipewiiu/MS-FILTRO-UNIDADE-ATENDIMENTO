package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UpaRepositoryGateway {

    Mono<Upa> createUpa(Upa upa);

    Mono<Upa> findUpaById(Long upaId);

    Flux<Upa> findAllUpa();

    Mono<Void> deleteUpaById(Long upaId);

    Mono<Upa> updateUpa(Long upa, Upa newUpa);

    Flux<Upa> findNearestUpa(Double latitude, Double longitude);

    Mono<Upa> findUpaWithLowerCapacity(Integer state);

    Mono<Upa> reduceServiceCapacityUpa(Long upaId);

    Mono<Upa> freesUpCapacityUpa(Long upaId);
}
