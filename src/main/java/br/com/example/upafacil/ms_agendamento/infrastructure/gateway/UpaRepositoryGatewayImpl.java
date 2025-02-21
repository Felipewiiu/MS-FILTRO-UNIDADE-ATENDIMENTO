package br.com.example.upafacil.ms_agendamento.infrastructure.gateway;

import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.infrastructure.mapper.upa.UpaMapper;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.repository.UpaRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

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


    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Raio da Terra em km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

}
