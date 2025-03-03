package br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.upaUtils;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.persistence.entity.UpaEntity;
import lombok.Getter;

@Getter
public class CalculateCapacityUsed {

    public static Double calculateCapacityUsed(UpaEntity upaEntity) {
        Integer capacity = upaEntity.getCapacity();
        Integer capacityUsed = upaEntity.getCapacityUsed();

        return (capacityUsed.doubleValue() / capacity.doubleValue()) * 100;

    }
}
