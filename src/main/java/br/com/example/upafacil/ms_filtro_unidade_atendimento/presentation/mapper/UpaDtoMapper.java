package br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.mapper;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto.UpaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpaDtoMapper {

    UpaDto toDto(Upa upa);

    Upa toDomain(UpaDto upaDto);
}
