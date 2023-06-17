package com.hasib.organization;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@OpenAPIDefinition( // To access swagger docs visit -> http://localhost:8090/swagger-ui/index.html#/
        info = @Info(
                title = "Organization Api",
                version = "1.0.0",
                description = "Organization Api Swagger Documentation",
                termsOfService = "janina",
                contact = @Contact(
                        name = "Team Mita",
                        email = "mita@gmail.com"
                ),
                license = @License(
                        name = "license",
                        url = "mita"
                )
        )
)
@EnableScheduling
public class OrganizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationApplication.class, args);
    }


    @Bean
    public WebClient bankApiClient() {
        return WebClient.create("http://localhost:8080/api");
    }
}
