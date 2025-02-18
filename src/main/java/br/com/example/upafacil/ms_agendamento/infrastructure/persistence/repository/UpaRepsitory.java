package br.com.example.upafacil.ms_agendamento.infrastructure.persistence.repository;

import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.entity.UpaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpaRepsitory extends ReactiveCrudRepository<UpaEntity, Long> {
}
