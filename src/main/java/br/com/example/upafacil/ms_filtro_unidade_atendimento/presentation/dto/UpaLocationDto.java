package br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto;

public record UpaLocationDto(
        String name,

        Integer capacity,

        Integer capacityUsed,

        String street,

        String city,

        Integer state,

        String zipCode,

        Double latitude,

        Double longitude,

        String url
) {

    public UpaLocationDto(String name, Integer capacity, Integer capacityUsed, String street, String city, Integer state, String zipCode, Double latitude, Double longitude, String url) {
        this.name = name;
        this.capacity = capacity;
        this.capacityUsed = capacityUsed;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = "https://www.google.com/maps/dir/?api=1&destination="+ latitude + "," + longitude + "&travelmode=driving";

    }

}
