package br.com.example.upafacil.ms_agendamento.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpaDto(

        String name,

        Integer capacity,

        String street,

        String city,

        Integer state,

        String zipCode,

        Double latitude,

        Double longitude
) {
}
