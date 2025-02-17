package br.com.example.upafacil.ms_agendamento.infrastructure.config;

import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.application.usecases.CreateUpaUseCase;
import br.com.example.upafacil.ms_agendamento.application.validators.UpaValidator;
import br.com.example.upafacil.ms_agendamento.infrastructure.gateway.UpaRepositoryGatewayImpl;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.repository.UpaRepsitory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UpaConfig {


    @Bean
    public UpaRepositoryGatewayImpl customUpaRepositoryGatewayImpl(UpaRepsitory upaRepsitory) {
        return new UpaRepositoryGatewayImpl(upaRepsitory);
    }



}
