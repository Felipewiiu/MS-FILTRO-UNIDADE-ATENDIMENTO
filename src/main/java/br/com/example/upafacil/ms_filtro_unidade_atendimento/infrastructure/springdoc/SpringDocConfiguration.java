package br.com.example.upafacil.ms_filtro_unidade_atendimento.infrastructure.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class SpringDocConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SpringDocConfiguration.class);

    public SpringDocConfiguration() {
        logger.debug("SpringDocConfiguration sendo instanciada");
    }

    String desc = "Microsserviço de filtro de unidades de atendimento, ";

    @Bean
    public OpenAPI customOpenAPI() {
        logger.debug("Inicializando customOpenAPI");
        return new OpenAPI()
                .info(new Info()
                        .title("MS-Filtro-Unidade-Atendimento")
                        .description(desc)
                        .contact(new Contact()
                                .name("Time backend")
                                .email("felipeadmy@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        logger.debug("Inicializando publicApi");
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}