package com.sm360.OnlineAdvertisement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Souhir Jedidi",
                        email = "souhirdjedidi@gmail.com",
                        url = ""
                ),
                description = "REST service for managing listings for online advertising service",
                title = "Online advertising",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://sm360advertising.com"
                )

        }
)
public class OpenApiConfig {
}
