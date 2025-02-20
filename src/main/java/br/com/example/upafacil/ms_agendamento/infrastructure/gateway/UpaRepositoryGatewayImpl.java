package br.com.example.upafacil.ms_agendamento.infrastructure.gateway;

import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.infrastructure.mapper.upa.UpaMapper;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.entity.UpaEntity;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.repository.UpaRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpaRepositoryGatewayImpl implements UpaRepositoryGateway {

    private final UpaRepsitory upaRepository;
    private final UpaMapper upaMapper;


    @Override
    public Mono<Upa> createUpa(Upa upa) {
        return upaRepository.save(upaMapper.toEntity(upa))
                .map(upaMapper::toDomain);
    }


    @Override
    public Mono<Upa> findUpaById(Long upaId) {
        return upaRepository.findById(upaId)
                .map(upaMapper::toDomain);
    }

    @Override
    public Flux<Upa> findAllUpa() {
        return upaRepository.findAll().map(upaMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteUpaById(Long upaId) {
        return upaRepository.deleteByUpaId(upaId);
    }

    @Override
    public Mono<Upa> updateUpa(Long upaId, Upa newUpa) {
        return upaRepository.findById(upaId)
                .flatMap(existingUpa -> {
                    existingUpa.upaUpdate(newUpa);
                    return upaRepository.save(existingUpa);
                })
                .map(upaMapper::toDomain);
    }
}
