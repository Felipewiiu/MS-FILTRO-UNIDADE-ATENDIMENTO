package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.validators.UpaValidator;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class CreateUpaUseCase {

    private final UpaRepositoryGateway upaRepositoryGateway;
    private final List<UpaValidator> upaValidators;


    public Mono<Upa> createUpa(Upa upa) {

        return Mono.when(upaValidators.stream()
                        .map(validator -> validator.validate(upa))
                        .toList()
                )
                .then(upaRepositoryGateway.createUpa(upa));
    }
}