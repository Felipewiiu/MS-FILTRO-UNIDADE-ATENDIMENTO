package templateDto;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto.UpaDto;

public class UpaTemplateDto {

    public static UpaDto upaTemplate(Long id, String name, Integer capacity, Integer capacityUsed, String street, String city, Integer state, String zipCode, Double latitude, Double longitude) {
        return new UpaDto(id, name, capacity, capacityUsed, street, city, state, zipCode, latitude, longitude);
    }

}
