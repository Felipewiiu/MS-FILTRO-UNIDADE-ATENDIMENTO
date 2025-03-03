package br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpaDto(

        Long upaId,

        String name,

        Integer capacity,

        Integer capacityUsed,

        String street,

        String city,

        Integer state,

        String zipCode,

        Double latitude,

        Double longitude
) {
}
