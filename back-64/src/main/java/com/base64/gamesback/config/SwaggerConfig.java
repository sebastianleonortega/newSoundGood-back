package com.base64.gamesback.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Bean para proporcionar una configuración personalizada de OpenAPI.
     *
     * @return OpenAPI: La configuración personalizada de OpenAPI.
     */
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
                )
                .info(new Info().title("Sound-good")
                        .description("Api para el proyecto de hipoacusia UFPSO")
                        .version("v0.0.1")
                        .termsOfService("")
                );
    }

}