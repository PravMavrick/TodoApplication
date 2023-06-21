package com.todoapplication.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class OpenApiConfig {

    @Value("${todo.openapi.dev-url}")
    private String devUrl;

    @Value("${todo.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("pravinchothe81@gmail.com");
        contact.setName("Todo Application");
        contact.setUrl("https://www.linkedin.com/in/pravin-chothe5963");

        License mitLicense = new License().name("MIT License").url("https://www.linkedin.com/in/pravin-chothe5963");

        Info info = new Info()
                .title("Todo Application API")
                .version("1.0")
                .contact(contact)
                .description("Todo application API.").termsOfService("https://www.linkedin.com/in/pravin-chothe5963")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(Arrays.asList(devServer, prodServer));
    }


}
