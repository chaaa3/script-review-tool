package com.example.scriptreviewtool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI scriptReviewOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Script Review Tool API")
                .description("API pour la gestion des scripts et leur processus de revue")
                .version("1.0")
                .contact(new Contact()
                    .name("Script Review Team")
                    .email("support@scriptreview.com")));
    }
}
