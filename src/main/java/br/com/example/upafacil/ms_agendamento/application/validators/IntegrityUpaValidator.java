package br.com.example.upafacil.ms_agendamento.application.validators;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import jakarta.validation.ValidationException;

public class IntegrityUpaValidator implements UpaValidator {

    @Override
    public void validate(Upa upa) {

        if(upa.getName() == null || upa.getName().isBlank()) {
            throw new ValidationException("O campo name não pode ser nulo ou em branco");
        }

        if(upa.getCapacity() == null || upa.getCapacity() <= 0) {
            throw new ValidationException("O campo capacity não pode ser nulo ou negativo");
        }

        if(upa.getStreet() == null || upa.getStreet().isBlank()) {
            throw new ValidationException("O campo street não pode ser nulo ou estar em branco");
        }

        if(upa.getCity() == null || upa.getCity().isBlank()) {
            throw new ValidationException("O Campo city não pode ser nulo ou estar em branco");
        }

        if(upa.getState() == null) {
            throw new ValidationException("O campo state não pode estar em branco");
        }

        if(upa.getZipCode() == null || upa.getZipCode().isBlank()) {
            throw new ValidationException("O campo zipCode não pode ser nulo ou estar em branco");
        }

        if(upa.getLatitude() == null) {
            throw new ValidationException("O campo latitude não pode ser nulo ou estar em branco");
        }
    }
}
