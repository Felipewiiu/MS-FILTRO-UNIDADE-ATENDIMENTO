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

    private String street;

    private String city;

    private Integer state;

    private String zipCode;

    private Double latitude;

    private Double longitude;

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
