package br.com.example.upafacil.ms_agendamento.infrastructure.persistence.entity;

import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.domain.enums.StateCountry;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("upa")
public class UpaEntity {

    @Id
    private Long upaId;

    private String name;

    private Integer capacity;

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

    public void upaUpdate(Upa upaDomain) {

        if(upaDomain.getName() != null) {
            this.name = upaDomain.getName();
        }

        if(upaDomain.getCapacity() != null) {
            this.capacity = upaDomain.getCapacity();
        }

        if(upaDomain.getStreet() != null) {
            this.street = upaDomain.getStreet();
        }

        if(upaDomain.getCity() != null) {
            this.city = upaDomain.getCity();
        }

        if(upaDomain.getState() != null) {
            this.state = upaDomain.getState();
        }

        if(upaDomain.getZipCode() != null) {
            this.zipCode = upaDomain.getZipCode();
        }

        if(upaDomain.getLatitude() != null) {
            this.latitude = upaDomain.getLatitude();
        }

        if(upaDomain.getLongitude() != null) {
            this.longitude = upaDomain.getLongitude();
        }


    }
}
