package PJfullstack.Inventario.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API 2026 INVENTARIO")
                        .version("1.1")
                        .description("Documentacion de API INVENTARIO para el sistema REPOSICION"));
    }
}