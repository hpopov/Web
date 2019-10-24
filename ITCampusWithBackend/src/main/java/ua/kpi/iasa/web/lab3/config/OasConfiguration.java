package ua.kpi.iasa.web.lab3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OasConfiguration {
    
    public static final String SECURITY_SCHEME = "jwtScheme";

    @Bean
    public OpenAPI customOpenAPI(@Value("${itcampus.version}") String appVersion) {
        return new OpenAPI()//@formatter:off
            .components(
                new Components()
                .addSecuritySchemes(SECURITY_SCHEME,
                    new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                )
            ).info(
                new Info()
                .title("ITCampus API")
                .version(appVersion)
                .description("Application for integration of students personal information and their IT projects"
                        + " to provide a convenient way of CV generation")
            );// @formatter:on
    }
}
