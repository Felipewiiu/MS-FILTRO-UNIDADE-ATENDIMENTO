package br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.persistence.entity.UpaEntity;
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

    private Integer capacityUsed = 0;

    private String street;

    private String city;

    private Integer state;

    private String zipCode;

    private Double latitude;

    private Double longitude;


    public static Double calculateCapacityUsed(Upa upa) {
        Integer capacity = upa.getCapacity();
        Integer capacityUsed = upa.getCapacityUsed();

        return (capacityUsed.doubleValue() / capacity.doubleValue()) * 100;

    }

    public Double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }


}
