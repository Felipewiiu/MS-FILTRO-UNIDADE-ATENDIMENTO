package br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.config;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.gateway.UpaRepositoryGateway;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases.*;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.validators.IntegrityUpaValidator;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.validators.UpaValidator;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.gateway.UpaRepositoryGatewayImpl;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.mapper.upa.UpaMapper;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.persistence.repository.UpaRepsitory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public IntegrityUpaValidator integrityUpaValidator(){
        return new IntegrityUpaValidator();
    }

    @Bean
    public FindNearestUpaUseCase customFindNearestUpaUseCase(
            @Qualifier("customUpaRepositoryGatewayImpl") UpaRepositoryGateway upaRepositoryGateway){
        return new FindNearestUpaUseCase(upaRepositoryGateway);
    }

    @Bean
    public FindUpaWithLowerCapacity customFindUpaWithLowerCapacity(
            @Qualifier("customUpaRepositoryGatewayImpl") UpaRepositoryGateway upaRepositoryGateway){
        return new FindUpaWithLowerCapacity(upaRepositoryGateway);
    }

    @Bean
    public ReduceServiceCapacityUpa customReduceServiceCapacityUpa(
            @Qualifier("customUpaRepositoryGatewayImpl") UpaRepositoryGateway upaRepositoryGateway){
        return new ReduceServiceCapacityUpa(upaRepositoryGateway);
    }

    @Bean
    public FreesUpaCapacityUpa customFreesUpaCapacityUpa(
            @Qualifier("customUpaRepositoryGatewayImpl") UpaRepositoryGateway upaRepositoryGateway){
        return new FreesUpaCapacityUpa(upaRepositoryGateway);
    }
}
