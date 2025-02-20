package br.com.example.upafacil.ms_agendamento.infrastructure.persistence.repository;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.entity.UpaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UpaRepsitory extends ReactiveCrudRepository<UpaEntity, Long> {

    Mono<Void> deleteByUpaId(Long upaId);
}
