package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.exeptions;

public class NotFoundUpaException extends RuntimeException {
    public NotFoundUpaException(Long upaId) {
        super("Upa not found with id: " + upaId);
    }
}
