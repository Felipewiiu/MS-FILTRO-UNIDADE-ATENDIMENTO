package br.com.example.upafacil.ms_agendamento.presentation.mapper;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.presentation.dto.UpaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UpaDtoMapper {

    @Mapping(target =   "upaId", ignore = true)
    UpaDto toDto(Upa upa);

    Upa toDomain(UpaDto upaDto);
}
