package br.com.example.upafacil.ms_agendamento.presentation.dto;

import br.com.example.upafacil.ms_agendamento.domain.enums.StateCountry;

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
