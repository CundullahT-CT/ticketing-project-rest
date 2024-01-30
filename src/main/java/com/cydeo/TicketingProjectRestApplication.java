package com.cydeo;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Users service REST API Documentation",
                description = "Users microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Ower",
                        email = "ower@gmail.com",
                        url = "https://www.cydeo.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.cudeo.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description =  "Users microservice REST API Documentation",
                url = "https://www.cydeo.com/swagger-ui.html"
        )
)
public class TicketingProjectRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketingProjectRestApplication.class, args);
    }

    @Bean
    public ModelMapper mapper(){

        return new ModelMapper();
    }

}
