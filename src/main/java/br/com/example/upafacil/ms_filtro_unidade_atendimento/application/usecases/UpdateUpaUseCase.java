package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.exeptions.NotFoundUpaException;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateUpaUseCase {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Mono<Upa> updateUpa(Long upaId, Upa newUpa) {
        return upaRepositoryGateway.findUpaById(upaId)
                .switchIfEmpty(Mono.error(new NotFoundUpaException(upaId)))
                .then(upaRepositoryGateway.updateUpa(upaId, newUpa));
    }
}
