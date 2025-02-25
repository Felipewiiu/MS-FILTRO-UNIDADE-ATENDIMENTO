package br.com.example.upafacil.ms_agendamento.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Upa {

    private Long upaId;

    private String name;

    private Integer capacity;

    private Integer capacityUsed;

    private String street;

    private String city;

    private Integer state;

    private String zipCode;

    private Double latitude;

    private Double longitude;


}
