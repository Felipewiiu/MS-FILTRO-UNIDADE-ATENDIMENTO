package br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.mapper.upa;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.persistence.entity.UpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UpaMapper {


    Upa toDomain(UpaEntity upaEntity);

    @Mapping(target = "capacityUsed", constant = "0")
    UpaEntity toEntity(Upa upa);
}
