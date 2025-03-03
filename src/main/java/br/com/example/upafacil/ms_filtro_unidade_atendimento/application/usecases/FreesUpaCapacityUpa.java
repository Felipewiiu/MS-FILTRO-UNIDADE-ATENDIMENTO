package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.exeptions.NotFoundUpaException;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FreesUpaCapacityUpa {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Mono<Upa> freesCapacity(Long upaId){
        return upaRepositoryGateway.freesUpCapacityUpa(upaId)
                .switchIfEmpty(Mono.error(new NotFoundUpaException(upaId)));
    }
}
