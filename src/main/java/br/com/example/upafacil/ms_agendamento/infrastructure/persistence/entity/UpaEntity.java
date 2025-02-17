package br.com.example.upafacil.ms_agendamento.infrastructure.persistence.entity;

import br.com.example.upafacil.ms_agendamento.domain.enums.StateCountry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("upa")
public class UpaEntity {

    @Id
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
