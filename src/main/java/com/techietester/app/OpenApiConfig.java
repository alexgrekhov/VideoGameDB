package com.techietester.app;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Video Game Database - Test Application")
                        .version("1.0.0")
                        .description("https://github.com/TechieTester/VideoGameDB")
                        .contact(new Contact().name("James Willett")));
    }
}
