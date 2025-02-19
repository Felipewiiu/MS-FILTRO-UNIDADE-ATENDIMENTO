package br.com.example.upafacil.ms_agendamento.infrastructure.mapper.upa;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.entity.UpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpaMapper {


    Upa toDomain(UpaEntity upaEntity);


    UpaEntity toEntity(Upa upa);
}
