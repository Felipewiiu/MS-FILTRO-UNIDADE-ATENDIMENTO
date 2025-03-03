package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.exeptions.NotFoundUpaException;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway.UpaRepositoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeletUpaUseCase {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Mono<Void> deleteUpaById(Long upaId) {
        return upaRepositoryGateway.findUpaById(upaId)
                .switchIfEmpty(Mono.error(new NotFoundUpaException(upaId)))
                .then(upaRepositoryGateway.deleteUpaById(upaId));
    }
}
