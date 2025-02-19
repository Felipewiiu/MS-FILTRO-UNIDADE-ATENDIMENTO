package br.com.example.upafacil.ms_agendamento.application.validators;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import reactor.core.publisher.Mono;

public interface UpaValidator {

    Mono<Void> validate(Upa upa);
}
