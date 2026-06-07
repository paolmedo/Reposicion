package com.reposicion.sucursal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfi {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API 2026 SUCURSAL")
                        .version("1.1")
                        .description("Documentacion de API SUCURSAL para el sistema REPOSICION"));
    }
}