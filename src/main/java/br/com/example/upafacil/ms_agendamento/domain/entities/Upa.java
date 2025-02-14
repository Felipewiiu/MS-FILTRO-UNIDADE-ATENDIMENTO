package br.com.example.upafacil.ms_agendamento.domain.entities;

import br.com.example.upafacil.ms_agendamento.domain.enums.StateCountry;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Upa {

    private Long upaId;

    private String name;

    private Integer capacity;

    private String street;

    private String city;

    private StateCountry state;

    private String zipCode;

    private Double latitude;

    private Double longitude;
}
