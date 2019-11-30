package br.com.codenation.errordashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket errorApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.codenation.errordashboard.endpoints   "))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Error Dashboard API",
                "Codenation Final Project - An Error Dashboard Application made with Spring Boot",
                "1.0.0-RELEASE",
                "Terms of service",
                new Contact("Leonardo Rodrigues", "", "lrodlim@gmail.com"),
                "License API",
                "License URL",
                Collections.emptyList());
    }

}
