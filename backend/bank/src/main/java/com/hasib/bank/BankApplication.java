package com.hasib.bank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition( // To access swagger docs visit -> http://localhost:8080/swagger-ui/index.html#/
        info = @Info(
                title = "Bank Api",
                version = "1.0.0",
                description = "Bank Api Swagger Documentation",
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
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

}
