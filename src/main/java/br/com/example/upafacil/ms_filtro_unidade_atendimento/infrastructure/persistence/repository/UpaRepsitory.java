package br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.persistence.repository;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.persistence.entity.UpaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UpaRepsitory extends ReactiveCrudRepository<UpaEntity, Long> {

    Mono<Void> deleteByUpaId(Long upaId);

    Flux<UpaEntity> findByState(Integer state);
}
