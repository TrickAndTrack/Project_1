package com.practice.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI studentManagementAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .description("Production level Spring Boot REST API documentation")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("API Support")
                                .email("support@company.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://company.com/docs"));
    }
}
