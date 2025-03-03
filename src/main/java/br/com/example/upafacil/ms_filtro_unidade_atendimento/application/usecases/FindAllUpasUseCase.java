package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindAllUpasUseCase {

    private final UpaRepositoryGateway upaRepositoryGateway;

    public Flux<Upa> findAllUpa() {

        return upaRepositoryGateway.findAllUpa();
    }
}
