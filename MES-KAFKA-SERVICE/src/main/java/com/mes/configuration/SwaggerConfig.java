package com.mes.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition
        /*(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Kafka Service API",
                version = "1.0",
                description = "Kafka Service API",
                termsOfService = "http://swagger.io/terms/")*//*,
        security = {
                @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "Bearer Token")
        }*//*)*/
/*@SecuritySchemes({
        @io.swagger.v3.oas.annotations.security.SecurityScheme(
                name = "Bearer Token",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT")
})*/
public class SwaggerConfig {

//    @Bean
//    public OpenAPI customOpenAPI(@Value("${swagger.host}") String swaggerHost) {
//        Server server = new Server();
//        server.setUrl(swaggerHost + "/kafka");
//        server.setDescription("host");
//        return new OpenAPI()
//                .servers(List.of(server));
//    }

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${openapi.service.title}") String serviceTitle,
            @Value("${openapi.service.version}") String serviceVersion,
            @Value("${openapi.service.url}") String url) {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .servers(List.of(new Server().url(url)))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
                .info(new Info().title(serviceTitle).version(serviceVersion));
    }

}
