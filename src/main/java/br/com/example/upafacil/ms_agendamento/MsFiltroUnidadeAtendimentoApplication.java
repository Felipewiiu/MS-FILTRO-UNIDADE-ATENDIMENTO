package br.com.example.upafacil.ms_agendamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsFiltroUnidadeAtendimentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFiltroUnidadeAtendimentoApplication.class, args);
	}

}
