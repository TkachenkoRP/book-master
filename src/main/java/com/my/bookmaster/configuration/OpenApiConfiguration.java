package com.my.bookmaster.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openApiDescription() {
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        localhostServer.setDescription("Local env");

        Contact contact = new Contact();
        contact.setName("Rodion Tkachenko");
        contact.setEmail("someemail@example");
        contact.setUrl("http://some.url");

        License mitLicence = new License().name("GNU AGPLv3")
                .url("https://choosealicenese.com/licesnse/agpl-3.0/");

        Info info = new Info()
                .title("Book Master API")
                .version("1.0")
                .contact(contact)
                .description("API for book")
                .termsOfService("http://some.terms.url")
                .license(mitLicence);

        final String securitySchemeName = "bearerAuth";

        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        Components components = new Components()
                .addSecuritySchemes(securitySchemeName, securityScheme);

        return new OpenAPI().info(info).servers(List.of(localhostServer))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(components);
    }
}
