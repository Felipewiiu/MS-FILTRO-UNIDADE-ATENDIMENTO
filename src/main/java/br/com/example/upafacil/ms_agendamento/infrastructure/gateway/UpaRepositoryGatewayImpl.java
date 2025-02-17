package br.com.example.upafacil.ms_agendamento.infrastructure.gateway;

import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.repository.UpaRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpaRepositoryGatewayImpl implements UpaRepositoryGateway {

     private final UpaRepsitory upaRepository;

    @Override
    public Upa createUpa(Upa upa) {
        return null;
    }

    @Override
    public Upa findUpaById(Long upaId) {
        return null;
    }

    @Override
    public List<Upa> findAllUpa() {
        return List.of();
    }

    @Override
    public void deleteUpaById(Long upaId) {

    }

    @Override
    public Upa updateUpa(Upa upa) {
        return null;
    }
}
