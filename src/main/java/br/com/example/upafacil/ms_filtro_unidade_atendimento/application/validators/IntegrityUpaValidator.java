package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.validators;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import jakarta.validation.ValidationException;
import reactor.core.publisher.Mono;


public class IntegrityUpaValidator implements UpaValidator {


    @Override
    public Mono<Void> validate(Upa upa) {
        if (upa.getName() == null || upa.getName().isBlank()) {
            return Mono.error(new ValidationException("O campo name não pode ser nulo ou em branco"));
        }

        if (upa.getCapacity() == null || upa.getCapacity() <= 0) {
            return Mono.error(new ValidationException("O campo capacity não pode ser nulo ou negativo"));
        }

        if (upa.getStreet() == null || upa.getStreet().isBlank()) {
            return Mono.error(new ValidationException("O campo street não pode ser nulo ou estar em branco"));
        }

        if (upa.getCity() == null || upa.getCity().isBlank()) {
            return Mono.error(new ValidationException("O Campo city não pode ser nulo ou estar em branco"));
        }

        if (upa.getState() == null) {
            return Mono.error(new ValidationException("O campo state não pode estar em branco"));
        }

        if (upa.getZipCode() == null || upa.getZipCode().isBlank()) {
            return Mono.error(new ValidationException("O campo zipCode não pode ser nulo ou estar em branco"));
        }

        if (upa.getLatitude() == null) {
            return Mono.error(new ValidationException("O campo latitude não pode ser nulo ou estar em branco"));
        }

        return Mono.empty();
    }

}
