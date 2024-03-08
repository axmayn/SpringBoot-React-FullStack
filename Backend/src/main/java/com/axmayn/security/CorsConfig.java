package com.axmayn.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Value("#{'${cors.allowed-origins}'.split(',')}")
    private List<String> allowedOrigin;

    @Value("#{'${cors.allowed-methods}'.split(',')}")
    private List<String> allowedMethod;



//    public CorsConfigurationSource configurationSource(){
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//
//        //corsConfiguration
//
//
//        return null;
//    };
}