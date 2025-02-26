package br.com.example.upafacil.ms_agendamento.infrastructure.mapper.upa;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.infrastructure.persistence.entity.UpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UpaMapper {


    Upa toDomain(UpaEntity upaEntity);

    @Mapping(target = "capacityUsed", constant = "0")
    UpaEntity toEntity(Upa upa);
}
