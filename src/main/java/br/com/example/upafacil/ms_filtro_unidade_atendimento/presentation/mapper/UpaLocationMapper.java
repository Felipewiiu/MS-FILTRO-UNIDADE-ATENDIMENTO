package br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.mapper;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto.UpaLocationDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UpaLocationMapper {

    @Mapping(target = "url", ignore = true)
    UpaLocationDto toDto(Upa upa);

    Upa fromDto(UpaLocationDto dto);

    @AfterMapping
    default void afterMapping(@MappingTarget UpaLocationDto dto) {
        dto = new UpaLocationDto(
                dto.name(),
                dto.capacity(),
                dto.capacityUsed(),
                dto.street(),
                dto.city(),
                dto.state(),
                dto.zipCode(),
                dto.latitude(),
                dto.longitude(),
                "https://www.google.com/maps?q=" + dto.latitude() + "," + dto.longitude()
        );
    }
}
