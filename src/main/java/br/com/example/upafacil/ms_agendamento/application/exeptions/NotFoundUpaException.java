package br.com.example.upafacil.ms_agendamento.application.exeptions;

public class NotFoundUpaException extends RuntimeException {
    public NotFoundUpaException(Long upaId) {
        super("Upa not found with id: " + upaId);
    }
}
