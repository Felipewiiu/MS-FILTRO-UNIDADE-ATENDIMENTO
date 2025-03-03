package br.com.example.upafacil.ms_filtro_unidade_atendimento.application.exeptions;

public class ExceededUpaCapacityException extends RuntimeException{
    public ExceededUpaCapacityException(){
        super("Capacidade máxima de atendimento atingida.");
    }
}
