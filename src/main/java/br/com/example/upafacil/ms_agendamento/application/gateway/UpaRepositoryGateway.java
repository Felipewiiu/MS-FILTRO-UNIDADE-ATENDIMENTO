package br.com.example.upafacil.ms_agendamento.application.gateway;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;

import java.util.List;

public interface UpaRepositoryGateway {

    Upa createUpa (Upa upa);

    Upa findUpaById(Long upaId);

    List<Upa> findAllUpa();

    void deleteUpaById(Long upaId);

    Upa updateUpa(Upa upa);
}
