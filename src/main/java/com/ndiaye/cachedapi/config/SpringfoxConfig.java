package com.ndiaye.cachedapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SpringfoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ndiaye.cachedapi.controller"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Ndiaye D.", "https://www.monsite.com", "nd.diabira@gmail.com");
        return new ApiInfo(
                "2iTech Paris 2 - Book manager with cache",
                "A simple CRUD API for books management using redis as caching system",
                "1.0",
                "https://www.m2i.com",
                contact,
                "Apache 2",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }


}
