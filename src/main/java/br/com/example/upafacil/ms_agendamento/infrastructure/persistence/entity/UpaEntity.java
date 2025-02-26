package br.com.example.upafacil.ms_agendamento.infrastructure.persistence.entity;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.domain.enums.StateCountry;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table("upa")
public class UpaEntity {

    @Id
    private Long upaId;

    private String name;

    private Integer capacity;

    private Integer capacityUsed = 0;

    private String street;

    private String city;

    private Integer state;

    private String zipCode;

    private Double latitude;

    private Double longitude;

    public UpaEntity(String name, Integer capacity, String street, String city, StateCountry state, String zipCode,
                     Double latitude, Double longitude) {
        this.name = name;
        this.capacity = capacity;
        this.street = street;
        this.city = city;
        setStateCountry(state);
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public StateCountry getStateCountry() {
        return StateCountry.valueOf(this.state.toString());
    }

    public void setStateCountry(StateCountry stateCountry) {
        if (stateCountry != null) {
            this.state = stateCountry.getCodigo();
        }
    }


}
