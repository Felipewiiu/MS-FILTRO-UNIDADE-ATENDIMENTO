package br.com.example.upafacil.ms_agendamento.presentation.dto;

public record UpaLocationDto(
        String name,

        Integer capacity,

        String street,

        String city,

        Integer state,

        String zipCode,

        Double latitude,

        Double longitude,

        String url
) {

    public UpaLocationDto(String name, Integer capacity, String street, String city, Integer state, String zipCode, Double latitude, Double longitude, String url) {
        this.name = name;
        this.capacity = capacity;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = "https://www.google.com/maps?q=" + latitude +"," + longitude;
    }

}
