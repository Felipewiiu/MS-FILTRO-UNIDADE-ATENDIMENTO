package br.com.example.upafacil.ms_agendamento.infrastructure.gateway;

import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.domain.upaUtils.CalculateCapacityUsed;
import br.com.example.upafacil.ms_agendamento.infrastructure.mapper.upa.UpaMapper;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.entity.UpaEntity;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.repository.UpaRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

import static br.com.example.upafacil.ms_agendamento.domain.upaUtils.CalculateHaversi.calculateDistance;

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
                    if (newUpa.getName() != null) existingUpa.setName(newUpa.getName());
                    if (newUpa.getCapacity() != null) existingUpa.setCapacity(newUpa.getCapacity());
                    if (newUpa.getStreet() != null) existingUpa.setStreet(newUpa.getStreet());
                    if (newUpa.getCity() != null) existingUpa.setCity(newUpa.getCity());
                    if (newUpa.getState() != null) existingUpa.setState(newUpa.getState());
                    if (newUpa.getZipCode() != null) existingUpa.setZipCode(newUpa.getZipCode());
                    if (newUpa.getLatitude() != null) existingUpa.setLatitude(newUpa.getLatitude());
                    if (newUpa.getLongitude() != null) existingUpa.setLongitude(newUpa.getLongitude());

                    return upaRepository.save(existingUpa);
                })
                .map(upaMapper::toDomain);
    }


    @Override
    public Mono<Upa> findNearestUpa(Double latitude, Double longitude) {

        return upaRepository.findAll()
                .collectList()
                .mapNotNull(upas -> upas.stream()
                        .min(Comparator.comparingDouble(upa -> calculateDistance(latitude, longitude, upa.getLatitude(), upa.getLongitude())))
                        .orElse(null)
                )
                .flatMap(upa -> upa == null ? Mono.empty() : Mono.just(upaMapper.toDomain(upa)));

    }

    @Override
    public Mono<Upa> findUpaWithLowerCapacity(Integer state) {
        Flux<UpaEntity> upaEntityFlux = upaRepository.findByState(state);

        return upaEntityFlux.collectList()
                .mapNotNull(upaEntities -> upaEntities.stream()
                        .min(Comparator.comparingDouble(CalculateCapacityUsed::calculateCapacityUsed))
                        .orElse(null)).map(upaMapper::toDomain);

    }



}
