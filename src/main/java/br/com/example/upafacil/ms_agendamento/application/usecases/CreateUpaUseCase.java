package br.com.example.upafacil.ms_agendamento.application.usecases;

import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.application.validators.UpaValidator;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateUpaUseCase {

    private final UpaRepositoryGateway upaRepositoryGateway;
    private final List<UpaValidator> upaValidator;

    public Upa createUpa(Upa upa) {

        upaValidator.forEach(validator -> validator.validate(upa));

        return upaRepositoryGateway.createUpa(upa);
    }
}
