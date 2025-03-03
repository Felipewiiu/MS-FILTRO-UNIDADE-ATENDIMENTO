package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.validators;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import reactor.core.publisher.Mono;

public interface UpaValidator {

    Mono<Void> validate(Upa upa);
}
