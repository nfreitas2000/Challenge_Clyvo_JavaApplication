package br.com.fiap.clyvo_java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SwaggerConfiguration {
	
	@Bean
	OpenAPI configurarSwagger() {
		return new OpenAPI().info(new Info()
				.title("Projeto CENN - FIAP 2026")
				.description("Projeto desenvolvido para a empresa Clyvo, com o objetivo de auxiliar o cuidado de animais domesticos")
				.summary("Projeto para o auxilio de animais")
				.termsOfService("Texto de Termos de Serviço")
				.version("1.0.0")
				.license(new License().url("/licenses")
						.name("Tela de Aquisição de Planos de Uso"))
				);
	}

}