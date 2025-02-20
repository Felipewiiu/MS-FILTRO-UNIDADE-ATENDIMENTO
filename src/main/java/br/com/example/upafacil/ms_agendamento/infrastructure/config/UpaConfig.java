package br.com.example.upafacil.ms_agendamento.infrastructure.config;

import br.com.example.upafacil.ms_agendamento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_agendamento.application.usecases.*;
import br.com.example.upafacil.ms_agendamento.application.validators.UpaValidator;
import br.com.example.upafacil.ms_agendamento.infrastructure.gateway.UpaRepositoryGatewayImpl;
import br.com.example.upafacil.ms_agendamento.infrastructure.mapper.upa.UpaMapper;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.repository.UpaRepsitory;
import br.com.example.upafacil.ms_agendamento.presentation.controller.UpaController;
import br.com.example.upafacil.ms_agendamento.presentation.mapper.UpaDtoMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class UpaConfig {


    @Bean
    public UpaRepositoryGatewayImpl customUpaRepositoryGatewayImpl(UpaRepsitory upaRepsitory, UpaMapper upaMapper) {
        return new UpaRepositoryGatewayImpl(upaRepsitory, upaMapper);
    }


    @Bean
    public CreateUpaUseCase customCreateUpaUseCase(
            @Qualifier("customUpaRepositoryGatewayImpl") UpaRepositoryGateway upaRepositoryGateway,
            List<UpaValidator> upaValidatorList) {
        return new CreateUpaUseCase(upaRepositoryGateway, upaValidatorList);
    }

    @Bean
    public FindUpaByIdUseCase customFindUpaByIdUseCase(
            @Qualifier("customUpaRepositoryGatewayImpl") UpaRepositoryGateway upaRepositoryGateway) {
        return new FindUpaByIdUseCase(upaRepositoryGateway);
    }

    @Bean
    public FindAllUpasUseCase customFindAllUpasUseCase(
            @Qualifier("customUpaRepositoryGatewayImpl") UpaRepositoryGateway upaRepositoryGateway) {
        return new FindAllUpasUseCase(upaRepositoryGateway);

    }

    @Bean
    public DeletUpaUseCase customDeletUpaUseCase(
            @Qualifier("customUpaRepositoryGatewayImpl") UpaRepositoryGateway upaRepositoryGateway) {
        return new DeletUpaUseCase(upaRepositoryGateway);

    }

    @Bean
    public UpdateUpaUseCase customUpdateUpaUseCase(
            @Qualifier("customUpaRepositoryGatewayImpl") UpaRepositoryGateway upaRepositoryGateway) {
        return new UpdateUpaUseCase(upaRepositoryGateway);
    }
}
