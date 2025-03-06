package br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.gateway;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.exeptions.ExceededUpaCapacityException;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.upaUtils.CalculateCapacityUsed;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.mapper.upa.UpaMapper;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.persistence.entity.UpaEntity;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.persistence.repository.UpaRepsitory;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.stream.Collectors;

import static br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.upaUtils.CalculateHaversine.calculateDistance;

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
                    if (newUpa.getCapacityUsed() != null) existingUpa.setCapacityUsed(newUpa.getCapacityUsed());
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
    public Flux<Upa> findNearestUpa(Double latitude, Double longitude) {
        return upaRepository.findAll()
                .sort(Comparator.comparingDouble(upa -> calculateDistance(latitude, longitude, upa.getLatitude(), upa.getLongitude())))
                .take(5)
                .map(upaMapper::toDomain);

    }

    @Override
    public Mono<Upa> findUpaWithLowerCapacity(Integer state) {
        Flux<UpaEntity> upaEntityFlux = upaRepository.findByState(state)
                .switchIfEmpty(Flux.empty());

        return upaEntityFlux.collectList()
                .mapNotNull(upaEntities -> upaEntities.stream()
                        .min(Comparator.comparingDouble(CalculateCapacityUsed::calculateCapacityUsed))
                        .orElse(null)).map(upaMapper::toDomain);

    }

    @Override
    public Mono<Upa> reduceServiceCapacityUpa(Long upaId) {
       return upaRepository.findById(upaId).flatMap( upa -> {
           if(upa.getCapacityUsed() < upa.getCapacity()){
               upa.setCapacityUsed(upa.getCapacityUsed() + 1) ;
               return upaRepository.save(upa);
           }else {
               return Mono.error( new ExceededUpaCapacityException());
           }
        }).map(upaMapper::toDomain);

    }


    @Override
    public Mono<Upa> freesUpCapacityUpa(Long upaId) {
        return upaRepository.findById(upaId).flatMap(upaEntity -> {
            if (upaEntity.getCapacityUsed() != 0) {
                upaEntity.setCapacityUsed(upaEntity.getCapacityUsed() - 1);
                return upaRepository.save(upaEntity);
            } else {
                return Mono.error(new ValidationException("Limite mínimo atingido"));
            }
        }).map(upaMapper::toDomain);
    }


}
