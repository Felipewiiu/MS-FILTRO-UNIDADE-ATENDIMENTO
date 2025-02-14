package br.com.example.upafacil.ms_agendamento.infrastructure.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    String desc = """
            # API de Gerenciamento de usuários mahallo kids\n
            
            O microsserviço user mahallo kids, foi criado para gerenciar usuários na plataforma e-comerce
            """;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("USER-MS")
                        .description(desc)
                        .contact(new Contact()
                                .name("Time backend")
                                .email("felipeadmy@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                        )
                );
    }
}
