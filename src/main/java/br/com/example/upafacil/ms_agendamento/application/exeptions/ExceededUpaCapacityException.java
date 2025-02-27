package br.com.example.upafacil.ms_agendamento.application.exeptions;

public class ExceededUpaCapacityException extends RuntimeException{
    public ExceededUpaCapacityException(){
        super("Capacidade máxima de atendimento atingida.");
    }
}
