package com.axmayn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("#{'${cors.allowed-origins}'.split(',')}")
    private List<String> allowedOrigin;

    @Value("#{'${cors.allowed-methods}'.split(',')}")
    private List<String> allowedMethod;

    public void addCorsMappings(CorsRegistry corsRegistry){

        CorsRegistration corsRegistration = corsRegistry.addMapping("/api/v1/**");
        allowedOrigin.forEach(corsRegistration::allowedOrigins);
        allowedMethod.forEach(corsRegistration::allowedMethods);

    }

}
